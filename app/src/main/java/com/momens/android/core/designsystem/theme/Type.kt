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

private object TypographyDefaults {
    // Title
    val TitleLetterSpacing = (-0.02).em
    val TitleLineHeight = 1.3.em

    // Body
    val BodyLetterSpacing = (-0.02).em
    val BodyLineHeight = 1.5.em

    // Caption
    val CaptionLetterSpacing = (-0.02).em
    val CaptionLineHeight = 1.5.em
}

val defaultMomensTypography = MomensTypography(

    // -------------------------
    // Title
    // -------------------------
    titleB24 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = TypographyDefaults.TitleLineHeight,
        letterSpacing = TypographyDefaults.TitleLetterSpacing
    ),

    titleB20 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = TypographyDefaults.TitleLineHeight,
        letterSpacing = TypographyDefaults.TitleLetterSpacing
    ),

    titleM20 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = TypographyDefaults.TitleLineHeight,
        letterSpacing = TypographyDefaults.TitleLetterSpacing
    ),

    // -------------------------
    // Body
    // -------------------------
    bodyB16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = TypographyDefaults.BodyLineHeight,
        letterSpacing = TypographyDefaults.BodyLetterSpacing
    ),

    bodyM16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = TypographyDefaults.BodyLineHeight,
        letterSpacing = TypographyDefaults.BodyLetterSpacing
    ),

    bodyB14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = TypographyDefaults.BodyLineHeight,
        letterSpacing = TypographyDefaults.BodyLetterSpacing
    ),

    bodyM14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = TypographyDefaults.BodyLineHeight,
        letterSpacing = TypographyDefaults.BodyLetterSpacing
    ),

    bodyB12 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = TypographyDefaults.BodyLineHeight,
        letterSpacing = TypographyDefaults.BodyLetterSpacing
    ),

    bodyM12 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = TypographyDefaults.BodyLineHeight,
        letterSpacing = TypographyDefaults.BodyLetterSpacing
    ),

    // -------------------------
    // Caption
    // -------------------------
    captionB11 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = TypographyDefaults.CaptionLineHeight,
        letterSpacing = TypographyDefaults.CaptionLetterSpacing
    ),

    captionM11 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = TypographyDefaults.CaptionLineHeight,
        letterSpacing = TypographyDefaults.CaptionLetterSpacing
    ),

    captionB10 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = TypographyDefaults.CaptionLineHeight,
        letterSpacing = TypographyDefaults.CaptionLetterSpacing
    ),

    captionM10 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = TypographyDefaults.CaptionLineHeight,
        letterSpacing = TypographyDefaults.CaptionLetterSpacing
    ),
)
