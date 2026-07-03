package com.momens.android.core.designsystem.component.progressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val fraction = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
            .background(
                color = MomensTheme.colors.primary50,
                shape = CircleShape
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = fraction)
                .widthIn(min = 14.dp)
                .fillMaxHeight()
                .background(
                    color = MomensTheme.colors.white,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Box(
                Modifier
                    .padding(end = 4.dp)
                    .size(6.dp)
                    .background(
                        color = MomensTheme.colors.primary50,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensProgressBarPreview() {
    MomensTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            repeat(11) { step ->
                MomensProgressBar(progress = step / 10f)
            }
        }
    }
}
