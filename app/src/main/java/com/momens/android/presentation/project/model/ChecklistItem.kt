package com.momens.android.presentation.project.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class ChecklistItem(
    val id: String,
    val title: String,
    val completed: Boolean,
)
