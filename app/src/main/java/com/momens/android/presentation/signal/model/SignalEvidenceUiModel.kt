package com.momens.android.presentation.signal.model

import androidx.compose.runtime.Immutable

@Immutable
data class SignalEvidenceUiModel(
    val sourceRefId: String,
    val source: SignalAccordionType,
    val time: String,
    val target: String,
    val change: String,
    val impact: String,
    val sourceUrl: String? = null,
)
