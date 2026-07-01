package com.momens.android.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShadowStyle(
    val offsetX: Dp,
    val offsetY: Dp,
    val blur: Dp,
    val spread: Dp,
    val color: Color,
)

@Immutable
data class MomensEffect(
    // Shadow
    val uiShadow: ShadowStyle,
    val bottomSheetShadow: ShadowStyle,

    // Blur
    val navBlur: Dp,
)

val defaultMomensEffect = MomensEffect(
    uiShadow = ShadowStyle(
        offsetX = 0.dp,
        offsetY = 2.dp,
        blur = 12.dp,
        spread = 0.dp,
        color = Color(0xFF000000).copy(alpha = 0.04f)
    ),

    bottomSheetShadow = ShadowStyle(
        offsetX = 0.dp,
        offsetY = (-6).dp,
        blur = 16.dp,
        spread = 0.dp,
        color = Color(0xFF000000).copy(alpha = 0.25f)
    ),

    navBlur = 4.dp
)
