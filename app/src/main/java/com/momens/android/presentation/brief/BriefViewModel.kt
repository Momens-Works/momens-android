package com.momens.android.presentation.brief

import androidx.lifecycle.ViewModel
import com.momens.android.core.common.state.UiState
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@HiltViewModel
class BriefViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<BriefUiState>>(
        UiState.Success(SampleBriefUiState),
    )
    val uiState: StateFlow<UiState<BriefUiState>> = _uiState.asStateFlow()

    fun selectSignalFilter(filterKey: String) {
        val firstPage = getSignalSummaryPage(
            filterKey = filterKey,
            cursor = null,
        )

        _uiState.update { currentState ->
            if (currentState is UiState.Success) {
                currentState.copy(
                    data = currentState.data.copy(
                        signalSummary = currentState.data.signalSummary.copy(
                            selectedFilterKey = filterKey,
                            items = firstPage.items,
                            nextCursor = firstPage.nextCursor,
                            isExpanded = false,
                            isLoadingMore = false,
                        ),
                    ),
                )
            } else {
                currentState
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

        val nextPage = getSignalSummaryPage(
            filterKey = signalSummary.selectedFilterKey,
            cursor = nextCursor,
        )

        _uiState.update { state ->
            if (state is UiState.Success) {
                val updatedItems = (
                    state.data.signalSummary.items + nextPage.items
                    ).toImmutableList()

                state.copy(
                    data = state.data.copy(
                        signalSummary = state.data.signalSummary.copy(
                            items = updatedItems,
                            nextCursor = nextPage.nextCursor,
                            isExpanded = true,
                            isLoadingMore = false,
                        ),
                    ),
                )
            } else {
                state
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

    private fun getSignalSummaryPage(
        filterKey: String,
        cursor: String?,
    ): BriefSignalSummaryPage {
        val filteredItems = if (filterKey == BriefSignalSummaryFilterKey.ALL) {
            sampleSignalSummaryItems
        } else {
            sampleSignalSummaryItems.filter { item -> item.typeKey == filterKey }
        }

        val offset = if (cursor == null) FIRST_PAGE_OFFSET else NEXT_PAGE_OFFSET
        val items = filteredItems
            .drop(offset)
            .take(PAGE_SIZE)
            .toImmutableList()
        val hasMore = filteredItems.size > offset + PAGE_SIZE

        return BriefSignalSummaryPage(
            items = items,
            nextCursor = if (hasMore) SAMPLE_NEXT_CURSOR else null,
        )
    }

    private data class BriefSignalSummaryPage(
        val items: ImmutableList<BriefSignalItemUiModel>,
        val nextCursor: String?,
    )

    private companion object {
        const val PAGE_SIZE = 3
        const val FIRST_PAGE_OFFSET = 0
        const val NEXT_PAGE_OFFSET = 3
        const val SAMPLE_NEXT_CURSOR =
            "MjAyNi0wNy0wM1QwMDowMDowMFp8M2I5ZTBkMTItNzhmNC00YTU2LThjMDEtOWQyZTNmNGE1YjZj"

        val sampleSignalSummaryItems = listOf(
            BriefSignalItemUiModel(
                id = "5c1a2b34-56d7-4e89-9f01-234a5b6c7d8e",
                typeKey = BriefSignalSummaryFilterKey.CHANGE,
                title = "권한 요청 반복 문의",
            ),
            BriefSignalItemUiModel(
                id = "6f3d8a61-4de7-4c01-9d2b-16fdf182e9a1",
                typeKey = BriefSignalSummaryFilterKey.DECISION,
                title = "소셜 로그인은 MVP 범위에서 제외",
            ),
            BriefSignalItemUiModel(
                id = "3b9e0d12-78f4-4a56-8c01-9d2e3f4a5b6c",
                typeKey = BriefSignalSummaryFilterKey.RISK,
                title = "Android 13+ 권한 요청 플로우 이탈 가능성",
            ),
            BriefSignalItemUiModel(
                id = "9d0a2b34-c678-4d90-8e12-3f4a5b6c7d8e",
                typeKey = BriefSignalSummaryFilterKey.QUESTION,
                title = "권한 요청 안내 문구 수정 필요",
            ),
            BriefSignalItemUiModel(
                id = "1e2f3a45-b789-4c01-9d23-4a5b6c7d8e9f",
                typeKey = BriefSignalSummaryFilterKey.QUESTION,
                title = "로그인 유지 기간 정책 결정 필요",
            ),
            BriefSignalItemUiModel(
                id = "27afd507-9c7f-4f0d-a2be-fcdab2477b19",
                typeKey = BriefSignalSummaryFilterKey.DECISION,
                title = "회원가입 MVP 범위 1차 확정",
            ),
        )
    }
}
