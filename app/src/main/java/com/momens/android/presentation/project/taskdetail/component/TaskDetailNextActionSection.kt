package com.momens.android.presentation.project.taskdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.component.textbox.MomensTextBox
import com.momens.android.core.designsystem.theme.MomensTheme

private const val EMPTY_RECOMMENDATION_TEXT = "목적과 완료 기준을 입력하면, 민수가 다음 행동을 제안해드려요."

@Composable
fun TaskDetailNextActionSection(
    recommendationText: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "다음행동",
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.gray900,
        )

        MomensTextBox(
            text = recommendationText ?: EMPTY_RECOMMENDATION_TEXT,
            iconResId = R.drawable.ic_minsu,
            onArrowClick = onClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskDetailNextActionSectionPreview() {
    MomensTheme {
        TaskDetailNextActionSection(
            modifier = Modifier.padding(16.dp),
            recommendationText = "민수가 추천해주는 다음행동이에요",
            onClick = {},
        )
    }
}

@Preview(showBackground = true, name = "빈 상태")
@Composable
private fun TaskDetailNextActionSectionEmptyPreview() {
    MomensTheme {
        TaskDetailNextActionSection(
            modifier = Modifier.padding(16.dp),
            recommendationText = null,
            onClick = {},
        )
    }
}
