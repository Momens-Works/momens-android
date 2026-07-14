package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SignalEvidenceSourceDto {
    @SerialName("slack")
    SLACK,

    @SerialName("github")
    GITHUB,

    @SerialName("figma")
    FIGMA,

    @SerialName("file")
    FILE,
}
