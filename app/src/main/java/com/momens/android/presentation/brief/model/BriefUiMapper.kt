package com.momens.android.presentation.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefPriorityResponse
import com.momens.android.data.brief.remote.dto.response.BriefProjectResponse
import com.momens.android.data.brief.remote.dto.response.BriefResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryFilterResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryItemResponse
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryResponse
import com.momens.android.presentation.brief.state.BriefUiState
import kotlinx.collections.immutable.toImmutableList

fun BriefResponse.toUiState(
    selectedFilterType: BriefSignalSummaryFilterType = BriefSignalSummaryFilterType.ALL,
): BriefUiState = BriefUiState(
    project = project.toUiModel(),
    signalSummary = signalSummary.toUiModel(selectedFilterType = selectedFilterType),
    priorities = priorities.map(BriefPriorityResponse::toUiModel).toImmutableList(),
)

fun BriefSignalSummaryItemResponse.toUiModel(): BriefSignalItemUiModel = BriefSignalItemUiModel(
    id = id,
    type = type.toFilterType(),
    title = title,
)

fun BriefSignalSummaryFilterType.toApiFilter(): String? {
    return if (this == BriefSignalSummaryFilterType.ALL) {
        null
    } else {
        name.lowercase()
    }
}

private fun BriefProjectResponse.toUiModel(): BriefProjectUiModel = BriefProjectUiModel(
    id = id,
    name = name,
    targetDate = targetDate.orEmpty(),
    progress = progress.toProgressFraction(),
    summary = summary.orEmpty(),
)

private fun BriefSignalSummaryResponse.toUiModel(
    selectedFilterType: BriefSignalSummaryFilterType,
): BriefSignalSummaryUiModel = BriefSignalSummaryUiModel(
    summary = summary,
    filters = filters.map(BriefSignalSummaryFilterResponse::toUiModel).toImmutableList(),
    selectedFilterType = selectedFilterType,
    items = items.map(BriefSignalSummaryItemResponse::toUiModel).toImmutableList(),
    nextCursor = nextCursor,
    isExpanded = false,
)

private fun BriefSignalSummaryFilterResponse.toUiModel(): BriefSignalSummaryFilter {
    val type = key.toFilterType()

    return BriefSignalSummaryFilter(
        type = type,
        label = label.ifBlank { type.name.lowercase().replaceFirstChar(Char::uppercaseChar) },
        count = count,
    )
}

private fun BriefPriorityResponse.toUiModel(): BriefPriorityUiModel = BriefPriorityUiModel(
    rank = rank,
    title = title,
    taskId = taskId,
)

private fun String.toFilterType(): BriefSignalSummaryFilterType {
    return BriefSignalSummaryFilterType.entries.firstOrNull { filterType ->
        filterType.name.equals(this, ignoreCase = true)
    } ?: BriefSignalSummaryFilterType.ALL
}

private fun Int.toProgressFraction(): Float {
    return if (this > 1) {
        this / 100f
    } else {
        toFloat()
    }.coerceIn(0f, 1f)
}
