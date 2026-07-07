package com.momens.android.core.designsystem.component.type

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

enum class ImportantLevel(
    val text: String,
    @param:DrawableRes val grayIcon: Int,
    @param:DrawableRes val blueIcon: Int,
) {
    LOW(
        text = "낮음",
        grayIcon = R.drawable.ic_importance_low_gray,
        blueIcon = R.drawable.ic_importance_low_blue,
    ),
    MEDIUM(
        text = "중간",
        grayIcon = R.drawable.ic_importance_medium_gray,
        blueIcon = R.drawable.ic_importance_medium_blue,
    ),
    HIGH(
        text = "높음",
        grayIcon = R.drawable.ic_importance_high_gray,
        blueIcon = R.drawable.ic_importance_high_blue,
    ),
}

enum class ImportantTone(
    val textStyle: @Composable () -> TextStyle,
    val textColor: @Composable () -> Color,
    val background: @Composable () -> Color,
) {
    GRAY(
        textStyle = { MomensTheme.typography.bodyM12 },
        textColor = { MomensTheme.colors.gray700 },
        background = { MomensTheme.colors.gray100 },
    ),
    BLUE(
        textStyle = { MomensTheme.typography.bodyB12 },
        textColor = { MomensTheme.colors.primary10 },
        background = { MomensTheme.colors.primary50 },
    ),
    CLEAR(
        textStyle = { MomensTheme.typography.bodyM12 },
        textColor = { MomensTheme.colors.gray600 },
        background = { Color.Unspecified },
    ),
}
