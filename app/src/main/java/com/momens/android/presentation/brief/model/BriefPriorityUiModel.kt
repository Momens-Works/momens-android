package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable

@Immutable
data class BriefPriorityUiModel(
    val rank: Int,
    val title: String,
    val taskId: String,
)
