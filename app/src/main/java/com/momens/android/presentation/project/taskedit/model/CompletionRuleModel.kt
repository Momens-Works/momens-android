package com.momens.android.presentation.project.taskedit.model

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Immutable

@Immutable
data class CompletionRuleModel(
    val id: CompletionIdModel,
    val state: TextFieldState,
    val isChecked: Boolean,
)
