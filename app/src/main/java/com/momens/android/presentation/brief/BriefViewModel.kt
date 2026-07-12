package com.momens.android.presentation.brief

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.state.UiState
import com.momens.android.data.brief.remote.dto.response.BriefPriorityResponse
import com.momens.android.data.brief.remote.dto.response.BriefProjectResponse
import com.momens.android.data.brief.remote.dto.response.BriefResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryFilterResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryItemResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryPageResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryResponse
import com.momens.android.data.brief.repository.BriefRepository
import com.momens.android.presentation.brief.model.BriefPriorityUiModel
import com.momens.android.presentation.brief.model.BriefProjectUiModel
import com.momens.android.presentation.brief.model.BriefSignalItemUiModel
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilter
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterKey
import com.momens.android.presentation.brief.model.BriefSignalSummaryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.collections.immutable.toImmutableList

@HiltViewModel
class BriefViewModel @Inject constructor(
    private val briefRepository: BriefRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<BriefUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<BriefUiState>> = _uiState.asStateFlow()

    fun loadBrief(projectId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            briefRepository.getBrief(projectId = projectId)
                .onSuccess { response ->
                    _uiState.value = UiState.Success(response.toUiState())
                }
                .onFailure {
                    _uiState.value = UiState.Failure
                }
        }
    }

    fun selectSignalFilter(filterKey: String) {
        val currentState = _uiState.value as? UiState.Success ?: return
        val projectId = currentState.data.project.id

        viewModelScope.launch {
            briefRepository.getSignalSummary(
                projectId = projectId,
                filter = filterKey,
                cursor = null,
                limit = null,
            ).onSuccess { response ->
                updateSignalSummaryPage(
                    response = response,
                    shouldAppend = false,
                    selectedFilterKey = filterKey,
                )
            }
        }
    }

    fun loadMoreSignalSummary() {
        val currentState = _uiState.value as? UiState.Success ?: return
        val signalSummary = currentState.data.signalSummary
        val nextCursor = signalSummary.nextCursor

        if (signalSummary.isLoadingMore) return

        if (nextCursor == null) {
            updateSignalSummaryExpanded(isExpanded = true)
            return
        }

        _uiState.update { state ->
            if (state is UiState.Success) {
                state.copy(
                    data = state.data.copy(
                        signalSummary = state.data.signalSummary.copy(
                            isExpanded = true,
                            isLoadingMore = true,
                        ),
                    ),
                )
            } else {
                state
            }
        }

        viewModelScope.launch {
            briefRepository.getSignalSummary(
                projectId = currentState.data.project.id,
                filter = signalSummary.selectedFilterKey,
                cursor = nextCursor,
                limit = null,
            ).onSuccess { response ->
                updateSignalSummaryPage(
                    response = response,
                    shouldAppend = true,
                    selectedFilterKey = null,
                )
            }.onFailure {
                updateSignalSummaryLoading(isLoadingMore = false)
            }
        }
    }

    fun foldSignalSummary() {
        updateSignalSummaryExpanded(isExpanded = false)
    }

    private fun updateSignalSummaryExpanded(isExpanded: Boolean) {
        _uiState.update { currentState ->
            if (currentState is UiState.Success) {
                currentState.copy(
                    data = currentState.data.copy(
                        signalSummary = currentState.data.signalSummary.copy(
                            isExpanded = isExpanded,
                        ),
                    ),
                )
            } else {
                currentState
            }
        }
    }

    private fun updateSignalSummaryPage(
        response: BriefSignalSummaryPageResponse,
        shouldAppend: Boolean,
        selectedFilterKey: String?,
    ) {
        _uiState.update { state ->
            if (state is UiState.Success) {
                val currentItems = state.data.signalSummary.items
                val newItems = response.items.map { item -> item.toUiModel() }
                val updatedItems = if (shouldAppend) {
                    currentItems + newItems
                } else {
                    newItems
                }

                state.copy(
                    data = state.data.copy(
                        signalSummary = state.data.signalSummary.copy(
                            items = updatedItems.toImmutableList(),
                            nextCursor = response.nextCursor,
                            selectedFilterKey = selectedFilterKey
                                ?: state.data.signalSummary.selectedFilterKey,
                            isExpanded = shouldAppend,
                            isLoadingMore = false,
                        ),
                    ),
                )
            } else {
                state
            }
        }
    }

    private fun updateSignalSummaryLoading(isLoadingMore: Boolean) {
        _uiState.update { state ->
            if (state is UiState.Success) {
                state.copy(
                    data = state.data.copy(
                        signalSummary = state.data.signalSummary.copy(
                            isLoadingMore = isLoadingMore,
                        ),
                    ),
                )
            } else {
                state
            }
        }
    }
}

private fun BriefResponse.toUiState(): BriefUiState {
    return BriefUiState(
        project = project.toUiModel(),
        signalSummary = signalSummary.toUiModel(),
        priorities = priorities.map { priority -> priority.toUiModel() }.toImmutableList(),
    )
}

private fun BriefProjectResponse.toUiModel(): BriefProjectUiModel {
    return BriefProjectUiModel(
        id = id,
        name = name,
        targetDate = targetDate.toBriefDateLabel(),
        progress = progress / PERCENT_DENOMINATOR,
        summary = summary,
    )
}

private fun BriefSignalSummaryResponse.toUiModel(): BriefSignalSummaryUiModel {
    return BriefSignalSummaryUiModel(
        summary = summary,
        filters = filters.map { filter -> filter.toUiModel() }.toImmutableList(),
        selectedFilterKey = BriefSignalSummaryFilterKey.ALL,
        items = items.map { item -> item.toUiModel() }.toImmutableList(),
        nextCursor = nextCursor,
        isExpanded = false,
    )
}

private fun BriefSignalSummaryFilterResponse.toUiModel(): BriefSignalSummaryFilter {
    return BriefSignalSummaryFilter(
        key = key,
        label = label,
        count = count,
    )
}

private fun BriefSignalSummaryItemResponse.toUiModel(): BriefSignalItemUiModel {
    return BriefSignalItemUiModel(
        id = id,
        typeKey = type,
        title = title,
    )
}

private fun BriefPriorityResponse.toUiModel(): BriefPriorityUiModel {
    return BriefPriorityUiModel(
        rank = rank,
        title = title,
        taskId = taskId,
    )
}

private fun String.toBriefDateLabel(): String {
    return runCatching {
        val date = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
        "${date.monthValue}월 ${date.dayOfMonth}일"
    }.getOrDefault(this)
}

private const val PERCENT_DENOMINATOR = 100f
