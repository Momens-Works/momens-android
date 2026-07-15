package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignalActionResultResponse(
    @SerialName("id")
    val id: String,
    @SerialName("action")
    val action: String,
)
