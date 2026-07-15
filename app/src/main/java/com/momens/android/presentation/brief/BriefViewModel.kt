package com.momens.android.presentation.brief

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.extension.updateSuccess
import com.momens.android.core.common.state.UiState
import com.momens.android.core.local.project.ProjectManager
import com.momens.android.data.brief.repository.BriefRepository
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterType
import com.momens.android.presentation.brief.model.toApiFilter
import com.momens.android.presentation.brief.model.toUiModel
import com.momens.android.presentation.brief.state.BriefUiState
import com.momens.android.presentation.brief.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class BriefViewModel @Inject constructor(
    projectManager: ProjectManager,
    private val briefRepository: BriefRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<BriefUiState>>(UiState.Loading)

    val uiState: StateFlow<UiState<BriefUiState>> = _uiState.asStateFlow()

    val projectContext = projectManager.observeProjectContext().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = projectManager.currentProjectContext,
    )

    fun loadBrief(projectId: String = projectContext.value.projectId.toString()) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            briefRepository.getBrief(projectId = projectId)
                .onSuccess { response ->
                    _uiState.value = UiState.Success(response.toUiState())
                }
                .onFailure {
                    _uiState.value = UiState.Failure
                }
        }
    }

    fun selectSignalFilter(filterType: BriefSignalSummaryFilterType) {
        val currentState = (_uiState.value as? UiState.Success)?.data ?: return
        val filter = filterType.toApiFilter()


        viewModelScope.launch {
            briefRepository.getSignalSummary(
                projectId = currentState.project.id,
                filter = filter,
                cursor = null,
                limit = 20,
            ).onSuccess { response ->
                _uiState.updateSuccess { state ->
                    state.copy(
                        signalSummary = state.signalSummary.copy(
                            selectedFilterType = filterType,
                            items = response.items.map { item -> item.toUiModel() }.toImmutableList(),
                            nextCursor = response.nextCursor,
                            isExpanded = false,
                        ),
                    )
                }
            }.onFailure {
            }
        }
    }

    fun loadMoreSignalSummary() {
        val currentState = (_uiState.value as? UiState.Success)?.data ?: return
        val signalSummary = currentState.signalSummary
        val nextCursor = signalSummary.nextCursor

        if (nextCursor == null) {
            updateSignalSummaryExpanded(isExpanded = true)
            return
        }

        val filter = signalSummary.selectedFilterType.toApiFilter()

        viewModelScope.launch {
            briefRepository.getSignalSummary(
                projectId = currentState.project.id,
                filter = filter,
                cursor = nextCursor,
                limit = 20,
            ).onSuccess { response ->
                _uiState.updateSuccess { state ->
                    state.copy(
                        signalSummary = state.signalSummary.copy(
                            items = (
                                state.signalSummary.items +
                                    response.items.map { item -> item.toUiModel() }
                                ).toImmutableList(),
                            nextCursor = response.nextCursor,
                            isExpanded = true,
                        ),
                    )
                }
            }.onFailure {
            }
        }
    }

    fun foldSignalSummary() {
        updateSignalSummaryExpanded(isExpanded = false)
    }

    private fun updateSignalSummaryExpanded(isExpanded: Boolean) {
        _uiState.updateSuccess { currentState ->
            currentState.copy(
                signalSummary = currentState.signalSummary.copy(isExpanded = isExpanded),
            )
        }
    }
}
