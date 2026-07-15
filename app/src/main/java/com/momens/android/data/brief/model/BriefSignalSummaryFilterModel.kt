package com.momens.android.data.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryFilterResponse

data class BriefSignalSummaryFilterModel(
    val key: String,
    val label: String,
    val count: Int,
)

fun BriefSignalSummaryFilterResponse.toModel(): BriefSignalSummaryFilterModel = BriefSignalSummaryFilterModel(
    key = key,
    label = label,
    count = count,
)
