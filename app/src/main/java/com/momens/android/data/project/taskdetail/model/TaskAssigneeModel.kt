package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskAssigneeDto

data class TaskAssigneeModel(
    val id: String,
    val name: String,
    val avatarUrl: String?,
)

fun TaskAssigneeDto.toModel(): TaskAssigneeModel = TaskAssigneeModel(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
)
