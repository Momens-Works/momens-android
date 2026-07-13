package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable

@Immutable
data class TaskDetailCompletionItemModel(
    val id: String,
    val title: String,
    val completed: Boolean,
)
