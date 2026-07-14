package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignalListResponse(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("signals")
    val signals: List<SignalSummaryDto>,
)
