package com.momens.android.presentation.project.task.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class TaskSectionUiModel(
    val type: MomensStatusEditType,
    val tasks: ImmutableList<TaskItemData> = persistentListOf(),
)
