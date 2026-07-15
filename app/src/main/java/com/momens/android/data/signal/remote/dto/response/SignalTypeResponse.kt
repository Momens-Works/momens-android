package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SignalTypeResponse {
    @SerialName("risk")
    RISK,

    @SerialName("change")
    CHANGE,

    @SerialName("decision")
    DECISION,

    @SerialName("question")
    QUESTION,
}
