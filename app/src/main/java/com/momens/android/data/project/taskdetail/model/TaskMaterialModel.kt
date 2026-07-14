package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskMaterialDto

data class TaskMaterialModel(
    val id: String,
    val title: String,
    val summary: String,
    val roles: List<String>,
    val kind: String,
    val sourceUrl: String,
)

fun TaskMaterialDto.toModel(): TaskMaterialModel = TaskMaterialModel(
    id = id,
    title = title,
    summary = summary,
    roles = roles,
    kind = kind,
    sourceUrl = sourceUrl,
)
