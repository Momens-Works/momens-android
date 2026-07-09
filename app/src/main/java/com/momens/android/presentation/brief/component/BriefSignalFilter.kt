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
import com.momens.android.core.designsystem.component.type.MomensSignalItem
import com.momens.android.core.designsystem.component.type.MomensSignalType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilter
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BriefSignalFilterSummary(
    selectedFilterType: BriefSignalSummaryFilterType,
    filters: ImmutableList<BriefSignalSummaryFilter>,
    summaries: ImmutableList<MomensSignalItem>,
    onFilterClick: (BriefSignalSummaryFilterType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BriefSignalFilterButton(
            selectedFilterType = selectedFilterType,
            filters = filters,
            onFilterClick = onFilterClick,
        )

        BriefDropdown(
            items = summaries,
        )
    }
}

@Composable
fun BriefSignalFilterButton(
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

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        visibleFilters
            .forEach { filter ->
                val type = if (filter.type == selectedVisibleFilterType) {
                    filter.type.chipButtonType
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

    val filters = persistentListOf(
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.ALL,
            label = "All",
            count = 0,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.DECISIONS,
            label = "Decision",
            count = 0,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.RISKS,
            label = "Risk",
            count = 1,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.QUESTIONS,
            label = "Question",
            count = 2,
        ),
        BriefSignalSummaryFilter(
            type = BriefSignalSummaryFilterType.CHANGES,
            label = "Change",
            count = 1,
        ),
    )

    val summaries = persistentListOf(
        MomensSignalItem(
            type = MomensSignalType.DECISION,
            text = "소셜 로그인은 MVP 범위에서 제외",
        ),
        MomensSignalItem(
            type = MomensSignalType.DECISION,
            text = "회원가입 MVP 범위 1차 확정",
        ),
        MomensSignalItem(
            type = MomensSignalType.CHANGE,
            text = "온보딩 문구 정책 변경",
        ),
        MomensSignalItem(
            type = MomensSignalType.QUESTION,
            text = "Android 13+ 권한 요청 플로우 이탈 가능성",
        ),
        MomensSignalItem(
            type = MomensSignalType.RISK,
            text = "Android 13+ 권한 요청 플로우 이탈 가능성",
        ),
    )

    MomensTheme {
        BriefSignalFilterSummary(
            selectedFilterType = selectedFilterType,
            filters = filters,
            summaries = summaries,
            onFilterClick = { filterType ->
                selectedFilterType = filterType
            },
        )
    }
}
