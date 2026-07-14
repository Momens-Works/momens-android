package com.momens.android.presentation.project.taskedit.model

import androidx.compose.runtime.Immutable
import com.momens.android.presentation.project.model.Assignee
import com.momens.android.presentation.project.model.ChecklistItem
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Immutable
@Serializable
data class TaskEditPayload(
    val assignee: Assignee? = null,
    val checklist: List<ChecklistItem> = emptyList(),
)

fun TaskEditPayload.toPayloadJson(): String = Json.encodeToString(this)
