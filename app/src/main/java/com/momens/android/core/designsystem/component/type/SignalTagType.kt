package com.momens.android.core.designsystem.component.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.momens.android.core.designsystem.theme.MomensTheme

enum class SignalTagType(
    val label: String,
    val background: @Composable () -> Color,
    val statusText: String,
) {
    RISK(
        label = "Risk",
        background = { MomensTheme.colors.pointRed },
        statusText = "Needs action",
    ),
    DECISION(
        label = "Decision",
        background = { MomensTheme.colors.pointPurple },
        statusText = "Needs review",
    ),
    CHANGE(
        label = "Change",
        background = { MomensTheme.colors.pointYellow },
        statusText = "Needs action",
    ),
    QUESTION(
        label = "Question",
        background = { MomensTheme.colors.pointMint },
        statusText = "Needs decision",
    ),
}
