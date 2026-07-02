package com.momens.android.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensProgressBar(
    percentage: Float,
    modifier: Modifier = Modifier,
) {
    val fraction = percentage.coerceIn(0f, 1f)

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
            .background(MomensTheme.colors.primary50, CircleShape)
    ) {
        val fillWidth = (maxWidth * fraction).coerceAtLeast(14.dp)

        Box(
            modifier = Modifier
                .width(fillWidth)
                .fillMaxHeight()
                .background(MomensTheme.colors.white, CircleShape),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Box(
                Modifier
                    .padding(end = 4.dp)
                    .size(6.dp)
                    .background(MomensTheme.colors.primary50, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensProgressBarPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        repeat(11) { step ->
            MomensProgressBar(percentage = step / 10f)
        }
    }
}
