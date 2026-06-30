package com.momens.android.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.momens.android.R

@Immutable
data class MomensTypography(
    // Title
    val titleBold24: TextStyle,
    val titleBold20: TextStyle,
    val titleMedium20: TextStyle,

    // Body
    val bodyBold16: TextStyle,
    val bodyMedium16: TextStyle,
    val bodyBold14: TextStyle,
    val bodyMedium14: TextStyle,
    val bodyBold12: TextStyle,
    val bodyMedium12: TextStyle,

    // Caption
    val captionBold11: TextStyle,
    val captionMedium11: TextStyle,
    val captionBold10: TextStyle,
    val captionMedium10: TextStyle,
)

private val SuitFontFamily = FontFamily(
    Font(R.font.suit_medium, weight = FontWeight.Medium),
    Font(R.font.suit_bold, weight = FontWeight.Bold),
)

val defaultMomensTypography = MomensTypography(

    // -------------------------
    // Title
    // -------------------------
    titleBold24 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 31.2.sp,
        letterSpacing = (-0.02).em
    ),

    titleBold20 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.02).em
    ),

    titleMedium20 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.02).em
    ),

    // -------------------------
    // Body
    // -------------------------
    bodyBold16 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).em
    ),

    bodyMedium16 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).em
    ),

    bodyBold14 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.02).em
    ),

    bodyMedium14 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.02).em
    ),

    bodyBold12 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.02).em
    ),

    bodyMedium12 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.02).em
    ),

    // -------------------------
    // Caption
    // -------------------------
    captionBold11 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.5.sp,
        letterSpacing = (-0.02).em
    ),

    captionMedium11 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.5.sp,
        letterSpacing = (-0.02).em
    ),

    captionBold10 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.02).em
    ),

    captionMedium10 = TextStyle(
        fontFamily = SuitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.02).em
    ),
)

val LocalMomensTypographyProvider = staticCompositionLocalOf {
    defaultMomensTypography
}
