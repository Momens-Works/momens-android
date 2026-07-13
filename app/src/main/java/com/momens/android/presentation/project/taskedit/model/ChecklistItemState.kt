package com.momens.android.presentation.project.taskedit.model

import androidx.compose.foundation.text.input.TextFieldState

data class ChecklistItemState(
    val itemId: String,
    val title: TextFieldState,
    val isChecked: Boolean,
)
