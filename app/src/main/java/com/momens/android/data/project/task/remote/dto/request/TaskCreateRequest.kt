package com.momens.android.data.project.task.remote.dto.request

import com.momens.android.data.project.task.model.TaskCreateModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskCreateRequest(
    @SerialName("title") val title: String,
    @SerialName("role") val role: String,
    @SerialName("priority") val priority: String,
)
