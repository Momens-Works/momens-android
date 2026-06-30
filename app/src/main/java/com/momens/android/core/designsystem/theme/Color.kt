package com.momens.android.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val Gray900 = Color(0xFF393939)
private val Gray800 = Color(0xFF4D4D4D)
private val Gray700 = Color(0xFF595959)
private val Gray600 = Color(0xFF737373)
private val Gray500 = Color(0xFFB6B6B6)
private val Gray400 = Color(0xFFCDCDCD)
private val Gray300 = Color(0xFFDBDBDB)
private val Gray200 = Color(0xFFF0F0F0)
private val Gray100 = Color(0xFFF5F5F5)

private val Blue300 = Color(0xFF2F363E)
private val Blue200 = Color(0xFF4A535A)
private val Blue100 = Color(0xFFB8BCBF)

private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF191919)

private val Yellow400 = Color(0xFFFFF9C4)
private val Yellow500 = Color(0xFFFEE500)
private val Yellow600 = Color(0xFFF6DE00)

private val Orange300 = Color(0xFFEA5B21)
private val Orange200 = Color(0xFFFF9117)
private val Orange100 = Color(0xFFFDEEE7)

@Immutable
data class MomensColors(
    val gray900: Color,
    val gray800: Color,
    val gray700: Color,
    val gray600: Color,
    val gray500: Color,
    val gray400: Color,
    val gray300: Color,
    val gray200: Color,
    val gray100: Color,

    val blue300: Color,
    val blue200: Color,
    val blue100: Color,

    val white: Color,
    val black: Color,

    val yellow400: Color,
    val yellow500: Color,
    val yellow600: Color,

    val orange300: Color,
    val orange200: Color,
    val orange100: Color
)

val defaultMomensColors = MomensColors(
    gray900 = Gray900,
    gray800 = Gray800,
    gray700 = Gray700,
    gray600 = Gray600,
    gray500 = Gray500,
    gray400 = Gray400,
    gray300 = Gray300,
    gray200 = Gray200,
    gray100 = Gray100,

    blue300 = Blue300,
    blue200 = Blue200,
    blue100 = Blue100,

    white = White,
    black = Black,

    yellow400 = Yellow400,
    yellow500 = Yellow500,
    yellow600 = Yellow600,

    orange300 = Orange300,
    orange200 = Orange200,
    orange100 = Orange100
)

val LocalMomensColorProvider = staticCompositionLocalOf {
    defaultMomensColors
}
