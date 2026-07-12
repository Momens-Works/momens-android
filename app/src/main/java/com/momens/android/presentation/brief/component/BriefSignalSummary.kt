package com.momens.android.presentation.brief.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun BriefSignalSummary(
    count: Int,
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        MomensSectionTitle(
            title = "시그널 요약",
            count = count.toString(),
            isEmphasized = true
        )

        BriefSignalSummaryContent(content = content)
    }
}

@Composable
private fun BriefSignalSummaryContent(
    content: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = content,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.white,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp,
            ),
        color = MomensTheme.colors.gray700,
        style = MomensTheme.typography.bodyM12,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFEDF0F4, widthDp = 320)
@Composable
private fun BriefSignalSummaryPreview() {
    MomensTheme {
        BriefSignalSummary(
            count = 5,
            content = "Android 권한 요청 이슈가 발견되었으며, 소셜 로그인은 MVP 범위에서 제외되었습니다. 이메일 회원가입과 온보딩 \n" +
                "이탈 개선이 우선적으로 필요합니다.",
        )
    }

}
