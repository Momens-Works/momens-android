package com.momens.android.presentation.brief

import androidx.lifecycle.ViewModel
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterType
import com.momens.android.presentation.brief.model.SampleBriefUiState
import com.momens.android.presentation.brief.state.BriefUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class BriefViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<BriefUiState>(SampleBriefUiState)

    val uiState: StateFlow<BriefUiState> = _uiState.asStateFlow()

    fun selectSignalFilter(filterType: BriefSignalSummaryFilterType) {
        _uiState.update { currentState ->
            currentState.copy(
                signalSummary = currentState.signalSummary.copy(
                    selectedFilterType = filterType,
                    items = getFilteredSignalItems(filterType),
                    isExpanded = false,
                ),
            )
        }
    }

    fun loadMoreSignalSummary() {
        updateSignalSummaryExpanded(isExpanded = true)
    }

    fun foldSignalSummary() {
        updateSignalSummaryExpanded(isExpanded = false)
    }

    private fun updateSignalSummaryExpanded(isExpanded: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                signalSummary = currentState.signalSummary.copy(
                    isExpanded = isExpanded,
                ),
            )
        }
    }

    private fun getFilteredSignalItems(
        filterType: BriefSignalSummaryFilterType,
    ) = if (filterType == BriefSignalSummaryFilterType.ALL) {
        SampleBriefUiState.signalSummary.items
    } else {
        SampleBriefUiState.signalSummary.items
            .filter { item -> item.type == filterType }
            .toImmutableList()
    }
}
