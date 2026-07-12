package com.momens.android.presentation.brief.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.momens.android.presentation.brief.BriefSignalItemUiModel
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilter
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterKey
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BriefSignalFilterSummary(
    selectedFilterKey: String,
    filters: ImmutableList<BriefSignalSummaryFilter>,
    summaries: ImmutableList<BriefSignalItemUiModel>,
    hasMoreSummaries: Boolean,
    isSummaryExpanded: Boolean,
    onFilterClick: (String) -> Unit,
    onSummaryMoreClick: () -> Unit,
    onSummaryFoldClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BriefSignalFilterButton(
            selectedFilterKey = selectedFilterKey,
            filters = filters,
            onFilterClick = onFilterClick,
        )

        BriefDropdown(
            items = summaries,
            hasMore = hasMoreSummaries,
            expanded = isSummaryExpanded,
            onMoreClick = onSummaryMoreClick,
            onFoldClick = onSummaryFoldClick,
        )
    }
}

@Composable
private fun BriefSignalFilterButton(
    selectedFilterKey: String,
    filters: ImmutableList<BriefSignalSummaryFilter>,
    onFilterClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visibleFilters = filters.filter { filter ->
        filter.key == BriefSignalSummaryFilterKey.ALL || filter.count > 0
    }

    val selectedVisibleFilterKey =
        visibleFilters.find { filter -> filter.key == selectedFilterKey }?.key
            ?: BriefSignalSummaryFilterKey.ALL

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        visibleFilters
            .forEach { filter ->
                val type = if (filter.key == selectedVisibleFilterKey) {
                    filter.chipButtonType
                } else {
                    MomensChipButtonType.WHITE
                }

                MomensChipButton(
                    label = filter.label,
                    count = filter.count,
                    type = type,
                    onClick = { onFilterClick(filter.key) },
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun BriefSignalSummarySectionPreview() {
    var selectedFilterKey by rememberSaveable {
        mutableStateOf(BriefSignalSummaryFilterKey.ALL)
    }
    var isSummaryExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    val filters = persistentListOf(
        BriefSignalSummaryFilter(
            key = BriefSignalSummaryFilterKey.ALL,
            label = "All",
            count = 0,
        ),
        BriefSignalSummaryFilter(
            key = BriefSignalSummaryFilterKey.DECISION,
            label = "Decision",
            count = 0,
        ),
        BriefSignalSummaryFilter(
            key = BriefSignalSummaryFilterKey.RISK,
            label = "Risk",
            count = 1,
        ),
        BriefSignalSummaryFilter(
            key = BriefSignalSummaryFilterKey.QUESTION,
            label = "Question",
            count = 2,
        ),
        BriefSignalSummaryFilter(
            key = BriefSignalSummaryFilterKey.CHANGE,
            label = "Change",
            count = 1,
        ),
    )

    val initialSummaries = persistentListOf(
        BriefSignalItemUiModel(
            id = "6f3d8a61-4de7-4c01-9d2b-16fdf182e9a1",
            typeKey = BriefSignalSummaryFilterKey.DECISION,
            title = "소셜 로그인은 MVP 범위에서 제외",
        ),
        BriefSignalItemUiModel(
            id = "27afd507-9c7f-4f0d-a2be-fcdab2477b19",
            typeKey = BriefSignalSummaryFilterKey.DECISION,
            title = "회원가입 MVP 범위 1차 확정",
        ),
        BriefSignalItemUiModel(
            id = "5c1a2b34-56d7-4e89-9f01-234a5b6c7d8e",
            typeKey = BriefSignalSummaryFilterKey.CHANGE,
            title = "온보딩 문구 정책 변경",
        ),
    )
    val expandedSummaries = persistentListOf(
        BriefSignalItemUiModel(
            id = "6f3d8a61-4de7-4c01-9d2b-16fdf182e9a1",
            typeKey = BriefSignalSummaryFilterKey.DECISION,
            title = "소셜 로그인은 MVP 범위에서 제외",
        ),
        BriefSignalItemUiModel(
            id = "27afd507-9c7f-4f0d-a2be-fcdab2477b19",
            typeKey = BriefSignalSummaryFilterKey.DECISION,
            title = "회원가입 MVP 범위 1차 확정",
        ),
        BriefSignalItemUiModel(
            id = "5c1a2b34-56d7-4e89-9f01-234a5b6c7d8e",
            typeKey = BriefSignalSummaryFilterKey.CHANGE,
            title = "온보딩 문구 정책 변경",
        ),
        BriefSignalItemUiModel(
            id = "9d0a2b34-c678-4d90-8e12-3f4a5b6c7d8e",
            typeKey = BriefSignalSummaryFilterKey.QUESTION,
            title = "Android 13+ 권한 요청 플로우 이탈 가능성",
        ),
        BriefSignalItemUiModel(
            id = "3b9e0d12-78f4-4a56-8c01-9d2e3f4a5b6c",
            typeKey = BriefSignalSummaryFilterKey.RISK,
            title = "Android 13+ 권한 요청 플로우 이탈 가능성",
        ),
    )

    MomensTheme {
        BriefSignalFilterSummary(
            selectedFilterKey = selectedFilterKey,
            filters = filters,
            summaries = if (isSummaryExpanded) expandedSummaries else initialSummaries,
            hasMoreSummaries = !isSummaryExpanded,
            isSummaryExpanded = isSummaryExpanded,
            onFilterClick = { filterKey ->
                selectedFilterKey = filterKey
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
