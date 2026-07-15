package com.momens.android.data.project.task.model

import com.momens.android.data.project.task.remote.dto.request.TaskCreateRequest

data class TaskCreateModel(
    val title: String,
    val role: String,
    val priority: String,
)

fun TaskCreateModel.toDto(): TaskCreateRequest = TaskCreateRequest(
    title = title,
    role = role,
    priority = priority,
)
