package com.momens.android.core.designsystem.effect

import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RenderEffect
import android.graphics.RectF
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun Modifier.momensUiShadow(
    shape: Shape = RoundedCornerShape(8.dp),
): Modifier = dropShadow(
    shape = shape,
    color = Color.Black.copy(alpha = 0.04f), // DS 적용 예정입니닷 ,,
    blur = 12.dp,
    offsetX = 0.dp,
    offsetY = 2.dp,
    spread = 0.dp,
)

@Composable
fun Modifier.momensBottomSheetShadow(
    shape: Shape
): Modifier = dropShadow(
    shape = shape,
    color = Color.Black.copy(alpha = 0.25f), //얘도  // DS 적용 예정입니닷 ,,
    blur = 16.dp,
    offsetX = 0.dp,
    offsetY = (-6).dp,
    spread = 0.dp,
)

fun Modifier.momensNavBlur(): Modifier = customBlur( 4.dp)

@Composable
fun Modifier.dropShadow(
    shape: Shape,
    color: Color,
    blur: Dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    spread: Dp = 0.dp,
): Modifier = composed {
    val density = LocalDensity.current

    val paint = remember(color, blur, density) {
        Paint().apply {
            this.color = color.toArgb()
            isAntiAlias = true
            val blurPx = with(density) { blur.toPx() }
            if (blurPx > 0f) {
                maskFilter = BlurMaskFilter(
                    blurPx,
                    BlurMaskFilter.Blur.NORMAL,
                )
            }
        }
    }

    drawBehind {
        val spreadPx = spread.toPx()
        val offsetXPx = offsetX.toPx()
        val offsetYPx = offsetY.toPx()

        val shadowWidth = size.width + spreadPx
        val shadowHeight = size.height + spreadPx

        if (shadowWidth <= 0f || shadowHeight <= 0f) return@drawBehind

        val shadowSize = Size(shadowWidth, shadowHeight)
        val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.translate(offsetXPx - spreadPx / 2f, offsetYPx - spreadPx / 2f)
            canvas.nativeCanvas.drawOutline(shadowOutline, paint)
            canvas.restore()
        }
    }
}

fun Modifier.customBlur(
    radius: Dp,
): Modifier = composed {
    val density = LocalDensity.current
    val radiusPx = remember(radius, density) {
        with(density) { radius.toPx() }
    }

    graphicsLayer {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && radiusPx > 0f) {
            renderEffect = RenderEffect
                .createBlurEffect(
                    radiusPx,
                    radiusPx,
                    Shader.TileMode.CLAMP,
                )
                .asComposeRenderEffect()
        }
    }
}

private fun android.graphics.Canvas.drawOutline(
    outline: Outline,
    paint: Paint,
) {
    when (outline) {
        is Outline.Generic -> drawPath(outline.path.asAndroidPath(), paint)
        is Outline.Rectangle -> {
            val rect = outline.rect
            drawRect(rect.left, rect.top, rect.right, rect.bottom, paint)
        }

        is Outline.Rounded -> {
            val roundRect = outline.roundRect
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

            drawPath(
                Path().apply {
                    addRoundRect(rect, radii, Path.Direction.CW)
                },
                paint,
            )
        }
    }
}
