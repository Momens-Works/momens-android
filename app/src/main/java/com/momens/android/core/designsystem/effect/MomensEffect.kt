package com.momens.android.core.designsystem.effect

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow as composeDropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

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

fun Modifier.momensNavBlur(): Modifier = customBlur(4.dp)

fun Modifier.dropShadow(
    shape: Shape,
    color: Color,
    blur: Dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    spread: Dp = 0.dp,
): Modifier = composeDropShadow(
    shape = shape,
    shadow = Shadow(
        radius = blur,
        color = color,
        spread = spread,
        offset = DpOffset(offsetX, offsetY),
    ),
)

fun Modifier.customBlur(
    radius: Dp,
): Modifier = graphicsLayer {
    val radiusPx = radius.toPx()

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
