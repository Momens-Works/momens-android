package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.presentation.project.model.ChecklistItem
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class TaskDetailChecklistModel(
    val completedCount: Int,
    val totalCount: Int,
    val items: ImmutableList<ChecklistItem>,
)
