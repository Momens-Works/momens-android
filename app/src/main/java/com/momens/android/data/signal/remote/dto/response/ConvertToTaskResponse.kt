package com.momens.android.data.signal.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConvertToTaskResponse(
    @SerialName("task")
    val task: TaskSummaryResponse,
    @SerialName("signal")
    val signal: SignalActionResultResponse,
)

@Serializable
data class TaskSummaryResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("status")
    val status: String,
)
