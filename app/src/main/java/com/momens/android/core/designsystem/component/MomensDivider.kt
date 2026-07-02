package com.momens.android.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensDivider(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(MomensTheme.colors.gray100),
    )
}

@Preview(showBackground = true)
@Composable
private fun MomensDividerPreview() {
    MomensTheme {
        Box(
            modifier = Modifier
                .height(720.dp)
                .width(360.dp),
        ) {
            MomensDivider(modifier = Modifier.align(alignment = Alignment.Center))
        }
    }
}
