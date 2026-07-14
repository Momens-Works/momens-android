package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignalDetailResponse(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: SignalTypeDto,
    @SerialName("title")
    val title: String,
    @SerialName("impact")
    val impact: String? = null,
    @SerialName("evidence")
    val evidence: List<SignalEvidenceDto>,
    @SerialName("minsu_suggestion")
    val minsuSuggestion: String? = null,
)
