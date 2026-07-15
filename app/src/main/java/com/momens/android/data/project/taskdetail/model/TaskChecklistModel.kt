package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskChecklistDto

data class TaskChecklistModel(
    val completedCount: Int,
    val totalCount: Int,
    val items: List<TaskChecklistItemModel>,
)

fun TaskChecklistDto.toModel(): TaskChecklistModel = TaskChecklistModel(
    completedCount = completedCount,
    totalCount = totalCount,
    items = items.map { it.toModel() },
)
