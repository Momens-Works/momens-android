package com.momens.android.presentation.project.task.edit.model

import androidx.compose.runtime.Immutable

@Immutable
data class CompletionRuleModel(
    val id: Long,
    val label: String,
    val completed: Boolean,
    val enabled: Boolean,
)
