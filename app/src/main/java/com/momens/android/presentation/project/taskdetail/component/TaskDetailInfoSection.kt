package com.momens.android.presentation.project.taskdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun TaskDetailInfoSection(
    role: String,
    assigneeName: String,
    priority: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray100,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "역할",
                style = MomensTheme.typography.bodyB12,
                color = MomensTheme.colors.gray800,
            )
            Text(
                text = "담당",
                style = MomensTheme.typography.bodyB12,
                color = MomensTheme.colors.gray800,
            )
            Text(
                text = "우선순위",
                style = MomensTheme.typography.bodyB12,
                color = MomensTheme.colors.gray800,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MomensTheme.colors.primary10,
                        shape = RoundedCornerShape(4.dp),
                    )
                    .padding(horizontal = 12.dp, vertical = 1.dp)
            ) {
                Text(
                    text = role,
                    color = MomensTheme.colors.gray600,
                    style = MomensTheme.typography.captionB10,
                )
            }

            Text(
                text = assigneeName,
                style = MomensTheme.typography.bodyM12,
                color = MomensTheme.colors.gray600,
            )
            Text(
                text = priority,
                style = MomensTheme.typography.bodyM12,
                color = MomensTheme.colors.gray600,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskDetailInfoSectionPreview() {
    MomensTheme {
        TaskDetailInfoSection(
            modifier = Modifier.padding(16.dp),
            role = "PM",
            assigneeName = "조원빈",
            priority = "중간",
        )
    }
}
