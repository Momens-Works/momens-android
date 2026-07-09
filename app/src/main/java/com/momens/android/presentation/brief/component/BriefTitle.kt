package com.momens.android.presentation.brief.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.dot.MomensDot
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun BriefTitle(
    title: String,
    count: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = MomensTheme.colors.gray900,
            style = MomensTheme.typography.bodyB14,
        )

        MomensDot()

        Text(
            text = count.toString(),
            color = MomensTheme.colors.gray900,
            style = MomensTheme.typography.bodyB14,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BriefTitlePreview() {
    MomensTheme {
        BriefTitle(
            title = "시그널 요약",
            count = 5,
        )
    }
}
