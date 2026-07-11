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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BriefCurrentPriority(
    priorities: ImmutableList<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        MomensSectionTitle(
            title = "현재 우선순위",
            count = 4.toString(),
            isEmphasized = true
        )

        BriefCurrentPriorityContent(
            priorities = priorities,
        )
    }
}

@Composable
private fun BriefCurrentPriorityContent(
    priorities: ImmutableList<String>,
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
        priorities.forEachIndexed { index, priority ->
            BriefCurrentPriorityItem(
                order = index + 1,
                priority = priority,
            )
        }
    }
}

@Composable
private fun BriefCurrentPriorityItem(
    order: Int,
    priority: String,
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
            text = priority,
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
                "이메일 회원가입 완료율 개선",
                "이메일 회원가입 완료율 개선",
                "이메일 회원가입 완료율 개선",
                "이메일 회원가입 완료율 개선",
            ),
        )
    }
}
