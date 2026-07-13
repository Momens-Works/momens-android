package com.momens.android.presentation.brief

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class BriefViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<BriefUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<BriefUiState>> = _uiState.asStateFlow()

    fun loadBrief(projectId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading


        }
    }

    fun selectSignalFilter(filterKey: String) {
        val currentState = _uiState.value as? UiState.Success ?: return
        val projectId = currentState.data.project.id

        viewModelScope.launch {
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
        shouldAppend: Boolean,
        selectedFilterKey: String?,
    ) {

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

private fun String.toBriefDateLabel(): String {
    return runCatching {
        val date = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
        "${date.monthValue}월 ${date.dayOfMonth}일"
    }.getOrDefault(this)
}

private const val PERCENT_DENOMINATOR = 100f
