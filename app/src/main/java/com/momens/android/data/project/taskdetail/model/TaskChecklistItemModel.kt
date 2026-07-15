package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskChecklistItemDto

data class TaskChecklistItemModel(
    val id: String,
    val title: String,
    val completed: Boolean,
)

fun TaskChecklistItemDto.toModel(): TaskChecklistItemModel = TaskChecklistItemModel(
    id = id,
    title = title,
    completed = completed,
)
