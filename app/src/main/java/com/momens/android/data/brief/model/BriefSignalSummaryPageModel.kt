package com.momens.android.data.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryPageResponse

data class BriefSignalSummaryPageModel(
    val items: List<BriefSignalSummaryItemModel>,
    val nextCursor: String?,
)

fun BriefSignalSummaryPageResponse.toModel(): BriefSignalSummaryPageModel = BriefSignalSummaryPageModel(
    items = items.map { it.toModel() },
    nextCursor = nextCursor,
)
