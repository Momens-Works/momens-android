package com.momens.android.data.project.taskdetail.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TaskRoleDto {
    @SerialName("pm")
    PM,

    @SerialName("design")
    DESIGN,

    @SerialName("frontend")
    FRONTEND,

    @SerialName("backend")
    BACKEND,
}
