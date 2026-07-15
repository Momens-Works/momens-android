package com.momens.android.presentation.project.task.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.data.project.task.model.TaskGroup
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Immutable
data class TaskSectionUiModel(
    val type: MomensStatusEditType,
    val tasks: ImmutableList<TaskItemData> = persistentListOf(),
)

fun TaskGroup.toUiModel(): TaskSectionUiModel = TaskSectionUiModel(
    type = groupKey.toStatusType(),
    tasks = tasks.map { it.toUiModel() }.toPersistentList(),
)

private fun String.toStatusType(): MomensStatusEditType =
    MomensStatusEditType.entries.firstOrNull { it.key == this }
        ?: MomensStatusEditType.TODO
