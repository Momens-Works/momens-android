package com.momens.android.core.designsystem.component.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.momens.android.core.designsystem.theme.MomensTheme

enum class SignalTagType(
    val label: String,
    val background: @Composable () -> Color,
){
    RISK(label = "Risk", background = { MomensTheme.colors.pointRed}),
    DECISION(label = "Decision", background = { MomensTheme.colors.pointPurple}),
    CHANGE(label = "Change", background = { MomensTheme.colors.pointYellow}),
    QUESTION(label = "Question", background = { MomensTheme.colors.pointMint}),
}
