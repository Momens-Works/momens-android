package com.momens.android.data.project.taskdetail.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TaskStatusDto {
    @SerialName("backlog")
    BACKLOG,

    @SerialName("todo")
    TODO,

    @SerialName("in_progress")
    IN_PROGRESS,

    @SerialName("done")
    DONE,

    @SerialName("cancelled")
    CANCELLED,
}
