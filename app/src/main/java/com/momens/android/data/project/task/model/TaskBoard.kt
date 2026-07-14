package com.momens.android.data.project.task.model

data class TaskBoard(
    val title: String,
    val description: String,
    val groups: List<TaskGroup>,
)

data class TaskGroup(
    val groupKey: String,
    val label: String,
    val count: Int,
    val tasks: List<TaskItem>,
)

data class TaskItem(
    val id: String,
    val title: String,
    val role: String,
    val priority: String,
    val materialCount: Int,
)
