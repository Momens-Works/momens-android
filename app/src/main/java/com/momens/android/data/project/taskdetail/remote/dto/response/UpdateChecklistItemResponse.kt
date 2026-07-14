package com.momens.android.data.project.taskdetail.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateChecklistItemResponse(
    @SerialName("checklist")
    val checklist: UpdateChecklistItemResultDto,
)

@Serializable
data class UpdateChecklistItemResultDto(
    @SerialName("completed_count")
    val completedCount: Int,

    @SerialName("total_count")
    val totalCount: Int,

    @SerialName("item")
    val item: TaskChecklistItemDto,
)
