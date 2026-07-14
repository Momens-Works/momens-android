package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DismissResponse(
    @SerialName("signal")
    val signal: SignalActionResultDto,
)
