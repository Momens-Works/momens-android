package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable

@Immutable
data class BriefProjectUiModel(
    val id: String,
    val name: String,
    val targetDate: String,
    val progress: Float,
    val summary: String,
)
