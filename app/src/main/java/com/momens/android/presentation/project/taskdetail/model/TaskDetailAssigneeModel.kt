package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.project.taskdetail.model.TaskAssigneeModel

@Immutable
data class TaskDetailAssigneeModel(
    val id: String,
    val name: String,
    val avatarUrl: String?,
)

fun TaskAssigneeModel.toUiModel(): TaskDetailAssigneeModel = TaskDetailAssigneeModel(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
)
