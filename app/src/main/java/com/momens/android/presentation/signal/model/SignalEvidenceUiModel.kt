package com.momens.android.presentation.signal.model

import androidx.compose.runtime.Immutable

@Immutable
data class SignalEvidenceUiModel(
    val id: Long,
    val source: SignalAccordionType,
    val time: String,
    val target: String,
    val change: String,
    val impact: String,
)
