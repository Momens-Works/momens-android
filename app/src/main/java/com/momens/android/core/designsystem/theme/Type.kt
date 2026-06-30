package com.momens.android.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class MomensTypography(
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val body4: TextStyle,
    val body5: TextStyle,
    val body6: TextStyle,
    val head1: TextStyle,
    val head2: TextStyle,
    val head3: TextStyle,
    val head4: TextStyle
)

private val PretendardFontFamily = FontFamily(
    Font(R.font.pretendard_medium, weight = FontWeight.Medium),
    Font(R.font.pretendard_bold, weight = FontWeight.Bold),
)

val defaultMomensTypography = MomensTypography(
    body1 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        letterSpacing = 0.01.em
    ),
    body2 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 22.5.sp,
        fontSize = 15.sp,
        letterSpacing = 0.01.em
    ),
    body3 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 18.2.sp,
        fontSize = 13.sp,
        letterSpacing = 0.01.em
    ),
    body4 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 18.sp,
        fontSize = 12.sp,
        letterSpacing = 0.01.em
    ),
    body5 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.5.sp,
        fontSize = 11.sp,
        letterSpacing = 0.01.em
    ),
    body6 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 12.sp,
        fontSize = 8.sp,
        letterSpacing = 0.01.em
    ),
    head1 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 33.sp,
        fontSize = 22.sp,
        letterSpacing = 0.05.em
    ),
    head2 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        letterSpacing = 0.05.em
    ),
    head3 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 21.sp,
        fontSize = 14.sp,
        letterSpacing = 0.05.em
    ),
    head4 = TextStyle(
        fontFamily = PretendardFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp,
        fontSize = 12.sp,
        letterSpacing = 0.05.em
    )
)

val LocalMomensTypographyProvider = staticCompositionLocalOf {
    defaultMomensTypography
}
