package com.momens.android.presentation.project.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun ProjectTaskCard(
    task: ProjectTaskUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MomensTheme.colors.white,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = task.title,
                    color = MomensTheme.colors.black,
                    style = MomensTheme.typography.bodyB12,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ProjectTaskChip(text = task.role)
                    Text(
                        text = "⚡⚡ ${task.priority}",
                        color = MomensTheme.colors.gray600,
                        style = MomensTheme.typography.captionM11,
                    )
                    Text(
                        text = "⌕ ${task.materialCount}",
                        color = MomensTheme.colors.gray600,
                        style = MomensTheme.typography.captionM11,
                    )
                }
            }
            Text(
                text = ">",
                color = MomensTheme.colors.black,
                style = MomensTheme.typography.titleB20,
            )
        }
    }
}

@Composable
private fun ProjectTaskChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = MomensTheme.colors.primary10,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            color = MomensTheme.colors.gray700,
            style = MomensTheme.typography.captionM11,
        )
    }
}
