package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class BriefSignalSummaryUiModel(
    val summary: String?,
    val filters: ImmutableList<BriefSignalSummaryFilter>,
    val selectedFilterKey: String,
    val items: ImmutableList<BriefSignalItemUiModel>,
    val nextCursor: String?,
    val isExpanded: Boolean,
    val isLoadingMore: Boolean = false,
) {
    val totalCount: Int
        get() = filters.firstOrNull { it.key == BriefSignalSummaryFilterKey.ALL }?.count ?: 0

    val hasMore: Boolean
        get() = nextCursor != null
}
