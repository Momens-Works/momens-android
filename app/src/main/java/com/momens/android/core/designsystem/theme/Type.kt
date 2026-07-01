package com.momens.android.core.designsystem.theme

import androidx.compose.runtime.Immutable
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
    val titleB24: TextStyle,
    val titleB20: TextStyle,
    val titleM20: TextStyle,

    // Body
    val bodyB16: TextStyle,
    val bodyM16: TextStyle,
    val bodyB14: TextStyle,
    val bodyM14: TextStyle,
    val bodyB12: TextStyle,
    val bodyM12: TextStyle,

    // Caption
    val captionB11: TextStyle,
    val captionM11: TextStyle,
    val captionB10: TextStyle,
    val captionM10: TextStyle,
)

private val suitFontFamily = FontFamily(
    Font(R.font.suit_medium, weight = FontWeight.Medium),
    Font(R.font.suit_bold, weight = FontWeight.Bold),
)

val defaultMomensTypography = MomensTypography(

    // -------------------------
    // Title
    // -------------------------
    titleB24 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 31.2.sp,
        letterSpacing = (-0.02).em
    ),

    titleB20 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.02).em
    ),

    titleM20 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.02).em
    ),

    // -------------------------
    // Body
    // -------------------------
    bodyB16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).em
    ),

    bodyM16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).em
    ),

    bodyB14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.02).em
    ),

    bodyM14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.02).em
    ),

    bodyB12 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.02).em
    ),

    bodyM12 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.02).em
    ),

    // -------------------------
    // Caption
    // -------------------------
    captionB11 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.5.sp,
        letterSpacing = (-0.02).em
    ),

    captionM11 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.5.sp,
        letterSpacing = (-0.02).em
    ),

    captionB10 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.02).em
    ),

    captionM10 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.02).em
    ),
)
