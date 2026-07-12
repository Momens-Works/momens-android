package com.momens.android.presentation.task.edit.model

import androidx.compose.runtime.Immutable

@Immutable
data class CompletionRuleModel(
    val id: CompletionIdModel,
    val label: String,
    val isChecked: Boolean,
    val enabled: Boolean,
)
