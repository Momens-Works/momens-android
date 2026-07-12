package com.momens.android.core.designsystem.component.dot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensDot(
    modifier: Modifier = Modifier,
    size: Dp = 2.dp,
    color: Color = MomensTheme.colors.gray900,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = color,
                shape = CircleShape,
            ),
    )
}
