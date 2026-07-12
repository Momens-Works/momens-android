package com.momens.android.presentation.task.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone

@Immutable
data class TaskItemData(
    val text: String,
    val label: String,
    val count: String,
    val level: ImportantLevel,
    val tone: ImportantTone
)
