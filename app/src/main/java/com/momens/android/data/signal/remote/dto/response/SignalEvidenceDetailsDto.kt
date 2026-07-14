package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignalEvidenceDetailsDto(
    @SerialName("target")
    val target: String,
    @SerialName("change")
    val change: String,
    @SerialName("impact")
    val impact: String,
)
