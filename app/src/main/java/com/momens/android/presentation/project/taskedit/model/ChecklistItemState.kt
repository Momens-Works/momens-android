package com.momens.android.presentation.project.taskedit.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChecklistItemState(
    val id: String,
    val title: String,
    val completed: Boolean,
)
