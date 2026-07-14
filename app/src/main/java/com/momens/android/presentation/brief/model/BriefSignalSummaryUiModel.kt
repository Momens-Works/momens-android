package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class BriefSignalSummaryUiModel(
    val summary: String?,
    val filters: ImmutableList<BriefSignalSummaryFilter>,
    val selectedFilterType: BriefSignalSummaryFilterType,
    val items: ImmutableList<BriefSignalItemUiModel>,
    val nextCursor: String?,
    val isExpanded: Boolean,
) {
    val totalCount: Int
        get() = filters.firstOrNull { it.type == BriefSignalSummaryFilterType.ALL }?.count ?: 0

    val hasMore: Boolean
        get() = nextCursor != null || items.size > DEFAULT_VISIBLE_COUNT
}

private const val DEFAULT_VISIBLE_COUNT = 3
