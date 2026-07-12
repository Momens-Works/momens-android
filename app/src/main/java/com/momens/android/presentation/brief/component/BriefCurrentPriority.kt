package com.momens.android.presentation.brief.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.brief.BriefPriorityUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BriefCurrentPriority(
    priorities: ImmutableList<BriefPriorityUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        MomensSectionTitle(
            title = "현재 우선순위",
            count = priorities.size.toString(),
            isEmphasized = true,
        )

        BriefCurrentPriorityContent(
            priorities = priorities,
        )
    }
}

@Composable
private fun BriefCurrentPriorityContent(
    priorities: ImmutableList<BriefPriorityUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                color = MomensTheme.colors.white,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        priorities.forEach { priority ->
            BriefCurrentPriorityItem(
                order = priority.rank,
                priority = priority,
            )
        }
    }
}

@Composable
private fun BriefCurrentPriorityItem(
    order: Int,
    priority: BriefPriorityUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "0$order",
            color = MomensTheme.colors.gray800,
            style = MomensTheme.typography.bodyB12,
            maxLines = 1,
        )

        Text(
            text = priority.title,
            color = MomensTheme.colors.gray600,
            style = MomensTheme.typography.bodyM12,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BriefCurrentPriorityPreview() {
    MomensTheme {
        BriefCurrentPriority(
            priorities = persistentListOf(
                BriefPriorityUiModel(
                    rank = 1,
                    title = "이메일 회원가입 완료율 개선",
                    taskId = "27afd507-9c7f-4f0d-a2be-fcdab2477b19",
                ),
                BriefPriorityUiModel(
                    rank = 2,
                    title = "Android 13+ 권한 요청 플로우 정비",
                    taskId = "4c8e1f23-a567-4b89-9c01-2d3e4f5a6b7c",
                ),
                BriefPriorityUiModel(
                    rank = 3,
                    title = "온보딩 이탈 구간 계측 추가",
                    taskId = "9d0a2b34-c678-4d90-8e12-3f4a5b6c7d8e",
                ),
                BriefPriorityUiModel(
                    rank = 4,
                    title = "소셜 로그인 제외 범위 QA",
                    taskId = "1e2f3a45-b789-4c01-9d23-4a5b6c7d8e9f",
                ),
            ),
        )
    }
}
