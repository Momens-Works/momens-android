package com.momens.android.presentation.project.task.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.data.project.task.model.TaskItem

@Immutable
data class TaskItemData(
    val id: String,
    val title: String,
    val role: MomensTaskButtonType,
    val priority: ImportantLevel,
    val materialCount: Int,
    val tone: ImportantTone,
)

fun TaskItem.toUiModel(): TaskItemData = TaskItemData(
    id = id,
    title = title,
    role = role.toButtonType(),
    priority = priority.toImportantLevel(),
    materialCount = materialCount,
    tone = ImportantTone.WHITE,
)

fun MomensTaskButtonType.toRequestValue(): String = name.lowercase()

fun ImportantLevel.toRequestValue(): String = name.lowercase()

private fun String.toButtonType(): MomensTaskButtonType =
    MomensTaskButtonType.entries.firstOrNull { it.name.equals(this, ignoreCase = true) }
        ?: MomensTaskButtonType.PM

private fun String.toImportantLevel(): ImportantLevel =
    ImportantLevel.entries.firstOrNull { it.name.equals(this, ignoreCase = true) }
        ?: ImportantLevel.LOW

