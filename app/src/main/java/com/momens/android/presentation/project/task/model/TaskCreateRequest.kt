package com.momens.android.presentation.project.task.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.ImportantLevel

@Immutable
data class TaskCreateRequest(
    val title: String,
    val role: MomensTaskButtonType,
    val priority: ImportantLevel,
)
