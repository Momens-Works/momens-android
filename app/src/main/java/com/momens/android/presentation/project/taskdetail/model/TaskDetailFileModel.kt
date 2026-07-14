package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.project.taskdetail.model.TaskMaterialModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class TaskDetailFileModel(
    val id: String,
    val title: String,
    val summary: String,
    val roles: ImmutableList<String>,
    val kind: String,
    val sourceUrl: String,
)

fun TaskMaterialModel.toUiModel(): TaskDetailFileModel = TaskDetailFileModel(
    id = id,
    title = title,
    summary = summary,
    roles = roles.toImmutableList(),
    kind = kind,
    sourceUrl = sourceUrl,
)
