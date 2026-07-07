package com.momens.android.core.designsystem.component.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.momens.android.core.designsystem.theme.MomensTheme

enum class MomensChipButtonType(
    val textStyle: @Composable () -> TextStyle,
    val textColor: @Composable () -> Color,
    val background: @Composable () -> Color,
) {
    PURPLE(
        textStyle = { MomensTheme.typography.captionM10 },
        textColor = { MomensTheme.colors.white },
        background = { MomensTheme.colors.pointPurple },
    ),
    RED(
        textStyle = { MomensTheme.typography.captionM10 },
        textColor = { MomensTheme.colors.white },
        background = { MomensTheme.colors.pointRed },
    ),
    BLACK(
        textStyle = { MomensTheme.typography.captionM10 },
        textColor = { MomensTheme.colors.white },
        background = { MomensTheme.colors.gray800 },
    ),
    WHITE(
        textStyle = { MomensTheme.typography.captionM10 },
        textColor = { MomensTheme.colors.gray800 },
        background = { MomensTheme.colors.white },
    ),
    YELLOW(
        textStyle = { MomensTheme.typography.captionM10 },
        textColor = { MomensTheme.colors.white },
        background = { MomensTheme.colors.pointYellow },
    ),
    MINT(
        textStyle = { MomensTheme.typography.captionM10 },
        textColor = { MomensTheme.colors.white },
        background = { MomensTheme.colors.pointMint },
    ),
}
