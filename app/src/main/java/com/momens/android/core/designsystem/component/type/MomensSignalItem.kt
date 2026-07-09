package com.momens.android.core.designsystem.component.type

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.momens.android.core.designsystem.theme.MomensTheme

@Immutable
data class MomensSignalItem(
    val type: MomensSignalType,
    val text: String,
)

enum class MomensSignalType(
    val color: @Composable () -> Color
) {
    RISK(color = { MomensTheme.colors.pointRed }),
    QUESTION(color = { MomensTheme.colors.pointMint }),
    DECISION(color = { MomensTheme.colors.pointPurple }),
}
