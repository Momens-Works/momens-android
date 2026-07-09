package com.momens.android.core.designsystem.component.pagetitle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensPageTitle(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MomensTheme.typography.titleB24,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            style = titleStyle,
            color = MomensTheme.colors.black
        )

        Text(
            text = subtitle,
            style = MomensTheme.typography.bodyM14,
            color = MomensTheme.colors.gray500,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensPageTitlePreview() {
    MomensTheme {
        MomensPageTitle(
            title = "오늘 확인해야 할 시그널",
            subtitle = "프로젝트의 의사결정에 영향을 줄 수 있는 변화입니다.",
        )
    }
}
