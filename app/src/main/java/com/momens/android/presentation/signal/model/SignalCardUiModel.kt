package com.momens.android.presentation.signal.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.SignalTagType

@Immutable
data class SignalCardUiModel(
    val id: String,
    val type: SignalTagType,
    val title: String,
    val impact: String,
    val minsuSuggestion: String,
)
