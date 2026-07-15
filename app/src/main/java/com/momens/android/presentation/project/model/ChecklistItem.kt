package com.momens.android.presentation.project.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.project.taskdetail.model.TaskChecklistItemModel
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class ChecklistItem(
    val id: String,
    val title: String,
    val completed: Boolean,
)

fun TaskChecklistItemModel.toUiModel(): ChecklistItem = ChecklistItem(
    id = id,
    title = title,
    completed = completed,
)
