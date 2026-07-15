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
    val signals: List<SignalSummaryResponse>,
)

@Serializable
data class SignalSummaryResponse(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: SignalTypeResponse,
    @SerialName("title")
    val title: String,
    @SerialName("impact")
    val impact: String? = null,
    @SerialName("minsu_suggestion")
    val minsuSuggestion: String? = null,
)
