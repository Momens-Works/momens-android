package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignalEvidenceDto(
    @SerialName("source_ref_id")
    val sourceRefId: String,
    @SerialName("source")
    val source: SignalEvidenceSourceDto,
    @SerialName("occurred_at")
    val occurredAt: String? = null,
    @SerialName("details")
    val details: SignalEvidenceDetailsDto,
    @SerialName("source_url")
    val sourceUrl: String? = null,
)
