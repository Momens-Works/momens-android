package com.momens.android.presentation.project.taskedit.model

import androidx.compose.runtime.Immutable
import com.momens.android.presentation.project.model.ChecklistItem
import java.util.UUID

@Immutable
data class ChecklistItemState(
    val id: String?,
    val localId: String,
    val title: String,
    val completed: Boolean,
)

fun ChecklistItem.toChecklistItemState(): ChecklistItemState = ChecklistItemState(
    id = id,
    localId = UUID.randomUUID().toString(),
    title = title,
    completed = completed,
)
