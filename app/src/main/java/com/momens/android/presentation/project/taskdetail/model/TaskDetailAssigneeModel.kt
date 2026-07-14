package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable

@Immutable
data class TaskDetailAssigneeModel(
    val id: String,
    val name: String,
    val avatarUrl: String?,
)
