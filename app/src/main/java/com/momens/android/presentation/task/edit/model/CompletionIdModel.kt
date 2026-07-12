package com.momens.android.presentation.task.edit.model

import androidx.compose.runtime.Immutable

@Immutable
data class CompletionIdModel(
    val taskId: String,
    val itemId: String,
)
