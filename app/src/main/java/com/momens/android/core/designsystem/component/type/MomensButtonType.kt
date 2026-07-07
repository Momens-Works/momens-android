package com.momens.android.core.designsystem.component.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.momens.android.core.designsystem.theme.MomensTheme

enum class MomensButtonType(
    val textStyle: @Composable () -> TextStyle,
    val textColor: @Composable () -> Color,
    val background: @Composable () -> Color,
) {
    PRIMARY(
        textStyle = { MomensTheme.typography.bodyB12 },
        textColor = { MomensTheme.colors.white },
        background = { MomensTheme.colors.primary50 },
    ),
    GRAY(
        textStyle = { MomensTheme.typography.bodyM12 },
        textColor = { MomensTheme.colors.gray700 },
        background = { MomensTheme.colors.gray100 },
    ),
    BLACK(
        textStyle = { MomensTheme.typography.bodyM12 },
        textColor = { MomensTheme.colors.white },
        background = { MomensTheme.colors.gray800 },
    ),
    WHITE(
        textStyle = { MomensTheme.typography.bodyM12 },
        textColor = { MomensTheme.colors.gray700 },
        background = { MomensTheme.colors.white },
    ),
}
