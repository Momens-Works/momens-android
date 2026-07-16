package com.momens.android.presentation.project.taskedit.util

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.dragDropItemModifier(
    dragDropState: DragDropState,
    isDragging: Boolean,
    isPrevious: Boolean,
    scale: Float,
    alpha: Float,
): Modifier = when {
    isDragging -> Modifier
        .zIndex(1f)
        .graphicsLayer {
            translationY = dragDropState.draggingItemOffset
            scaleX = scale
            scaleY = scale
            this.alpha = alpha
            shadowElevation = 8.dp.toPx()
            shape = RoundedCornerShape(8.dp)
            clip = true
        }

    isPrevious -> Modifier
        .zIndex(1f)
        .graphicsLayer {
            translationY = dragDropState.previousItemOffset.value
            scaleX = scale
            scaleY = scale
            this.alpha = alpha
        }

    else -> Modifier.animateItem(
        placementSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
    )
}
