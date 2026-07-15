package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignalDetailResponse(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: SignalTypeResponse,
    @SerialName("title")
    val title: String,
    @SerialName("impact")
    val impact: String? = null,
    @SerialName("evidence")
    val evidence: List<SignalEvidenceResponse>,
    @SerialName("minsu_suggestion")
    val minsuSuggestion: String? = null,
)

@Serializable
data class SignalEvidenceResponse(
    @SerialName("source_ref_id")
    val sourceRefId: String,
    @SerialName("source")
    val source: SignalEvidenceSourceResponse,
    @SerialName("occurred_at")
    val occurredAt: String? = null,
    @SerialName("details")
    val details: SignalEvidenceDetailsResponse,
    @SerialName("source_url")
    val sourceUrl: String? = null,
)

@Serializable
enum class SignalEvidenceSourceResponse {
    @SerialName("SOURCE_TYPE_SLACK")
    SLACK,

    @SerialName("SOURCE_TYPE_GITHUB")
    GITHUB,

    @SerialName("SOURCE_TYPE_FIGMA")
    FIGMA,

    @SerialName("SOURCE_TYPE_FILE")
    FILE,
}

@Serializable
data class SignalEvidenceDetailsResponse(
    @SerialName("target")
    val target: String,
    @SerialName("change")
    val change: String,
    @SerialName("impact")
    val impact: String? = null,
)
