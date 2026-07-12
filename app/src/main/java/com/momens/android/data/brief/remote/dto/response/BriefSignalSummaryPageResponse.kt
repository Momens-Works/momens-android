package com.momens.android.data.brief.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BriefSignalSummaryPageResponse(
    @SerialName("items")
    val items: List<BriefSignalSummaryItemResponse> = emptyList(),
    @SerialName("next_cursor")
    val nextCursor: String?,
)
