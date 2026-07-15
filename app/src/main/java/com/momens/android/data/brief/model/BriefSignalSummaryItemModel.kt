package com.momens.android.data.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryItemResponse

data class BriefSignalSummaryItemModel(
    val id: String,
    val type: String,
    val title: String,
)

fun BriefSignalSummaryItemResponse.toModel(): BriefSignalSummaryItemModel = BriefSignalSummaryItemModel(
    id = id,
    type = type,
    title = title,
)
