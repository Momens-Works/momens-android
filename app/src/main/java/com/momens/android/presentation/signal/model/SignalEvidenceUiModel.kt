package com.momens.android.presentation.signal.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensAccordionType

@Immutable
data class SignalEvidenceUiModel(
    val id: Long,
    val source: MomensAccordionType,
    val time: String,
    val target: String,
    val change: String,
    val impact: String,
)
