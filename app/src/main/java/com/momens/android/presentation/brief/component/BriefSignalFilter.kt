package com.momens.android.presentation.brief.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.button.MomensChipButton
import com.momens.android.core.designsystem.component.type.MomensChipButtonType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.brief.model.BriefSignalItemUiModel
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilter
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BriefSignalFilterSummary(
    selectedFilterType: BriefSignalSummaryFilterType,
    filters: ImmutableList<BriefSignalSummaryFilter>,
    summaries: ImmutableList<BriefSignalItemUiModel>,
    hasMoreSummaries: Boolean,
    canLoadMoreSummaries: Boolean,
    isSummaryExpanded: Boolean,
    onFilterClick: (BriefSignalSummaryFilterType) -> Unit,
    onSummaryMoreClick: () -> Unit,
    onSummaryFoldClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BriefSignalFilterButton(
            selectedFilterType = selectedFilterType,
            filters = filters,
            onFilterClick = onFilterClick,
        )

        BriefDropdown(
            items = summaries,
            hasMore = hasMoreSummaries,
            canLoadMore = canLoadMoreSummaries,
            expanded = isSummaryExpanded,
            onMoreClick = onSummaryMoreClick,
            onLoadMore = onSummaryMoreClick,
            onFoldClick = onSummaryFoldClick,
        )
    }
}

@Composable
private fun BriefSignalFilterButton(
    selectedFilterType: BriefSignalSummaryFilterType,
    filters: ImmutableList<BriefSignalSummaryFilter>,
    onFilterClick: (BriefSignalSummaryFilterType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visibleFilters = filters.filter { filter ->
        filter.type == BriefSignalSummaryFilterType.ALL || filter.count > 0
    }

    val selectedVisibleFilterType =
        visibleFilters.find { filter -> filter.type == selectedFilterType }?.type
            ?: BriefSignalSummaryFilterType.ALL

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(
            items = visibleFilters,
            key = { filter -> filter.type },
        ) { filter ->
            val type = if (filter.type == selectedVisibleFilterType) {
                filter.chipButtonType
            } else {
                MomensChipButtonType.WHITE
            }

            MomensChipButton(
                label = filter.label,
                count = filter.count,
                type = type,
                onClick = { onFilterClick(filter.type) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BriefSignalSummarySectionPreview() {
    var selectedFilterType by rememberSaveable {
        mutableStateOf(BriefSignalSummaryFilterType.ALL)
    }
    var isSummaryExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    val filters = persistentListOf(
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.ALL,
            label = "All",
            count = 0,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.DECISION,
            label = "Decision",
            count = 0,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.RISK,
            label = "Risk",
            count = 1,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.QUESTION,
            label = "Question",
            count = 2,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.CHANGE,
            label = "Change",
            count = 1,
        ),
    )

    val initialSummaries = persistentListOf(
        BriefSignalItemUiModel(
            id = "6f3d8a61-4de7-4c01-9d2b-16fdf182e9a1",
            type = BriefSignalSummaryFilterType.DECISION,
            title = "소셜 로그인은 MVP 범위에서 제외",
        ),
        BriefSignalItemUiModel(
            id = "27afd507-9c7f-4f0d-a2be-fcdab2477b19",
            type = BriefSignalSummaryFilterType.DECISION,
            title = "회원가입 MVP 범위 1차 확정",
        ),
        BriefSignalItemUiModel(
            id = "5c1a2b34-56d7-4e89-9f01-234a5b6c7d8e",
            type = BriefSignalSummaryFilterType.CHANGE,
            title = "온보딩 문구 정책 변경",
        ),
    )
    val expandedSummaries = persistentListOf(
        BriefSignalItemUiModel(
            id = "6f3d8a61-4de7-4c01-9d2b-16fdf182e9a1",
            type = BriefSignalSummaryFilterType.DECISION,
            title = "소셜 로그인은 MVP 범위에서 제외",
        ),
        BriefSignalItemUiModel(
            id = "27afd507-9c7f-4f0d-a2be-fcdab2477b19",
            type = BriefSignalSummaryFilterType.DECISION,
            title = "회원가입 MVP 범위 1차 확정",
        ),
        BriefSignalItemUiModel(
            id = "5c1a2b34-56d7-4e89-9f01-234a5b6c7d8e",
            type = BriefSignalSummaryFilterType.CHANGE,
            title = "온보딩 문구 정책 변경",
        ),
        BriefSignalItemUiModel(
            id = "9d0a2b34-c678-4d90-8e12-3f4a5b6c7d8e",
            type = BriefSignalSummaryFilterType.QUESTION,
            title = "Android 13+ 권한 요청 플로우 이탈 가능성",
        ),
        BriefSignalItemUiModel(
            id = "3b9e0d12-78f4-4a56-8c01-9d2e3f4a5b6c",
            type = BriefSignalSummaryFilterType.RISK,
            title = "Android 13+ 권한 요청 플로우 이탈 가능성",
        ),
    )

    MomensTheme {
        BriefSignalFilterSummary(
            selectedFilterType = selectedFilterType,
            filters = filters,
            summaries = if (isSummaryExpanded) expandedSummaries else initialSummaries,
            hasMoreSummaries = !isSummaryExpanded,
            canLoadMoreSummaries = false,
            isSummaryExpanded = isSummaryExpanded,
            onFilterClick = { filterType ->
                selectedFilterType = filterType
            },
            onSummaryMoreClick = {
                isSummaryExpanded = true
            },
            onSummaryFoldClick = {
                isSummaryExpanded = false
            },
        )
    }
}
