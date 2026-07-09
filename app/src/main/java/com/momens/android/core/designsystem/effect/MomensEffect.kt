package com.momens.android.core.designsystem.effect

import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.blur.HazeColorEffect
import dev.chrisbanes.haze.blur.blurEffect
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

class MomensNavBlurState internal constructor(
    internal val hazeState: HazeState,
)

@Composable
fun Modifier.momensUiShadow(
    shape: Shape = RoundedCornerShape(8.dp),
): Modifier = dropShadow(
    shape = shape,
    color = MomensTheme.colors.black.copy(alpha = 0.04f),
    blur = 12.dp,
    offsetX = 0.dp,
    offsetY = 2.dp,
    spread = 0.dp,
)

@Composable
fun Modifier.momensBottomSheetShadow(
    shape: Shape,
): Modifier = dropShadow(
    shape = shape,
    color = MomensTheme.colors.black.copy(alpha = 0.25f),
    blur = 16.dp,
    offsetX = 0.dp,
    offsetY = (-6).dp,
    spread = 0.dp,
)

@Composable
fun rememberMomensNavBlurState(): MomensNavBlurState = MomensNavBlurState(
    hazeState = rememberHazeState(),
)

fun Modifier.momensNavBlurSource(
    state: MomensNavBlurState,
): Modifier = hazeSource(state = state.hazeState)

@Composable
fun Modifier.momensNavBlur(
    state: MomensNavBlurState? = null,
): Modifier {
    val navColor = MomensTheme.colors.navGray

    return if (state != null) {
        hazeEffect(state = state.hazeState) {
            blurEffect {
                blurRadius = 4.dp
                colorEffects = listOf(HazeColorEffect.tint(navColor))
                noiseFactor = 0f
            }
        }
    } else {
        this
    }
}

fun Modifier.dropShadow(
    shape: Shape,
    color: Color,
    blur: Dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    spread: Dp = 0.dp,
): Modifier = drawWithCache {
    val blurPx = blur.toPx()
    val paint = Paint().apply {
        this.color = color.toArgb()
        isAntiAlias = true
        if (blurPx > 0f) {
            maskFilter = BlurMaskFilter(
                blurPx,
                BlurMaskFilter.Blur.NORMAL,
            )
        }
    }

    val spreadPx = spread.toPx()
    val offsetXPx = offsetX.toPx()
    val offsetYPx = offsetY.toPx()

    val shadowWidth = size.width + spreadPx * 2f
    val shadowHeight = size.height + spreadPx * 2f

    if (shadowWidth <= 0f || shadowHeight <= 0f) {
        return@drawWithCache onDrawBehind {}
    }

    val shadowSize = Size(shadowWidth, shadowHeight)
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)
    val shadowPath = shadowOutline.toAndroidPath()

    onDrawBehind {
        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.translate(offsetXPx - spreadPx, offsetYPx - spreadPx)
            canvas.nativeCanvas.drawOutline(shadowOutline, shadowPath, paint)
            canvas.restore()
        }
    }
}

private fun Canvas.drawOutline(
    outline: Outline,
    path: Path?,
    paint: Paint,
) {
    when (outline) {
        is Outline.Generic -> path?.let { drawPath(it, paint) }
        is Outline.Rectangle -> {
            val rect = outline.rect
            drawRect(rect.left, rect.top, rect.right, rect.bottom, paint)
        }

        is Outline.Rounded -> path?.let { drawPath(it, paint) }
    }
}

private fun Outline.toAndroidPath(): Path? {
    return when (this) {
        is Outline.Generic -> path.asAndroidPath()
        is Outline.Rectangle -> null
        is Outline.Rounded -> {
            val roundRect = this.roundRect
            val rect = RectF(roundRect.left, roundRect.top, roundRect.right, roundRect.bottom)
            val radii = floatArrayOf(
                roundRect.topLeftCornerRadius.x,
                roundRect.topLeftCornerRadius.y,
                roundRect.topRightCornerRadius.x,
                roundRect.topRightCornerRadius.y,
                roundRect.bottomRightCornerRadius.x,
                roundRect.bottomRightCornerRadius.y,
                roundRect.bottomLeftCornerRadius.x,
                roundRect.bottomLeftCornerRadius.y,
            )

            Path().apply {
                addRoundRect(rect, radii, Path.Direction.CW)
            }
        }
    }
}
