package com.momens.android.presentation.project.task.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone

@Immutable
data class TaskItemData(
    val id: String,
    val title: String,
    val role: MomensTaskButtonType,
    val priority: ImportantLevel,
    val materialCount: Int,
    val tone: ImportantTone,
)
