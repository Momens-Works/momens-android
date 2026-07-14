package com.momens.android.data.project.taskdetail.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateChecklistItemRequest(
    @SerialName("completed")
    val completed: Boolean,
)
