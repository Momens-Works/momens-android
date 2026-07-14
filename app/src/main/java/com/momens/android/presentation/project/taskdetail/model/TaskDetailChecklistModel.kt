package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.project.taskdetail.model.TaskChecklistModel
import com.momens.android.presentation.project.model.ChecklistItem
import com.momens.android.presentation.project.model.toUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class TaskDetailChecklistModel(
    val completedCount: Int,
    val totalCount: Int,
    val items: ImmutableList<ChecklistItem>,
)

fun TaskChecklistModel.toUiModel(): TaskDetailChecklistModel = TaskDetailChecklistModel(
    completedCount = completedCount,
    totalCount = totalCount,
    items = items.map { it.toUiModel() }.toImmutableList(),
)
