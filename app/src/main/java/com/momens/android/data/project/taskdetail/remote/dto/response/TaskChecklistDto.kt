package com.momens.android.data.project.taskdetail.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskChecklistDto(
    @SerialName("completed_count")
    val completedCount: Int,

    @SerialName("total_count")
    val totalCount: Int,

    @SerialName("items")
    val items: List<TaskChecklistItemDto>,
)
