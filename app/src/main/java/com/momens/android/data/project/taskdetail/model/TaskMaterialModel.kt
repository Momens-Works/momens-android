package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskMaterialDto
import com.momens.android.data.project.taskdetail.remote.dto.response.TaskMaterialKindDto

data class TaskMaterialModel(
    val id: String,
    val title: String,
    val summary: String,
    val roles: List<String>,
    val kind: TaskMaterialKindModel,
    val sourceUrl: String,
    val createdAt: String?,
)

enum class TaskMaterialKindModel {
    SLACK,
    GITHUB,
    FIGMA,
    FILE,
}

fun TaskMaterialDto.toModel(): TaskMaterialModel = TaskMaterialModel(
    id = id,
    title = title,
    summary = summary,
    roles = roles,
    kind = kind.toModel(),
    sourceUrl = sourceUrl,
    createdAt = createdAt,
)

fun TaskMaterialKindDto.toModel(): TaskMaterialKindModel = when (this) {
    TaskMaterialKindDto.SLACK -> TaskMaterialKindModel.SLACK
    TaskMaterialKindDto.GITHUB -> TaskMaterialKindModel.GITHUB
    TaskMaterialKindDto.FIGMA -> TaskMaterialKindModel.FIGMA
    TaskMaterialKindDto.FILE -> TaskMaterialKindModel.FILE
}
