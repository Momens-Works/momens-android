package com.momens.android.data.project.taskdetail.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskOpenQuestionDto(
    @SerialName("id")
    val id: String,

    @SerialName("body")
    val body: String,
)
