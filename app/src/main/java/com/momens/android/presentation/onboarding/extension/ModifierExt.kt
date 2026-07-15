package com.momens.android.presentation.onboarding.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.coachmarkTarget(
    onPositioned: (Rect) -> Unit,
): Modifier {
    return onGloballyPositioned { coordinates ->
        onPositioned(coordinates.boundsInRoot())
    }
}

@Composable
fun Modifier.coachmarkTarget(
    onPositioned: (Rect) -> Unit,
    startPadding: Dp,
): Modifier {
    val density = LocalDensity.current

    return onGloballyPositioned { coordinates ->
        val bounds = coordinates.boundsInRoot()

        onPositioned(
            Rect(
                left = bounds.left - with(density) { startPadding.toPx() },
                top = bounds.top,
                right = bounds.right,
                bottom = bounds.bottom,
            ),
        )
    }
}
