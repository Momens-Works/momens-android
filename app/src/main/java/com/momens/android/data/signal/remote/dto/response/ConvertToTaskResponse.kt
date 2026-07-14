package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConvertToTaskResponse(
    @SerialName("task")
    val task: TaskSummaryDto,
    @SerialName("signal")
    val signal: SignalActionResultDto,
)
