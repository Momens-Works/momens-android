package com.momens.android.data.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryResponse

data class BriefSignalSummaryModel(
    val summary: String?,
    val filters: List<BriefSignalSummaryFilterModel>,
    val items: List<BriefSignalSummaryItemModel>,
    val nextCursor: String?,
)

fun BriefSignalSummaryResponse.toModel(): BriefSignalSummaryModel = BriefSignalSummaryModel(
    summary = summary,
    filters = filters.map { it.toModel() },
    items = items.map { it.toModel() },
    nextCursor = nextCursor,
)
