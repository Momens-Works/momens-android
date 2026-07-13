package com.momens.android.presentation.project.taskedit.model

import androidx.compose.runtime.Immutable

@Immutable
data class CompletionIdModel(
    val taskId: String,
    val itemId: String,
)
