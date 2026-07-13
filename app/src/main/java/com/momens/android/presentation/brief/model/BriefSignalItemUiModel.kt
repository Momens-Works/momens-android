package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable

@Immutable
data class BriefSignalItemUiModel(
    val id: String,
    val type: BriefSignalSummaryFilterType,
    val title: String,
)
