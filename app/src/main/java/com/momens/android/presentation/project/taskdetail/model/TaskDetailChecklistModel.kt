package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class TaskDetailChecklistModel(
    val completedCount: Int,
    val totalCount: Int,
    val items: ImmutableList<TaskDetailCompletionItemModel>,
)
