package com.momens.android.presentation.project.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun ProjectTaskSection(
    section: ProjectTaskSectionUiModel,
    onTaskClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ProjectTaskSectionHeader(section = section)
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            section.tasks.forEach { task ->
                ProjectTaskCard(
                    task = task,
                    onClick = { onTaskClick(task.id) },
                )
            }
        }
    }
}

@Composable
private fun ProjectTaskSectionHeader(
    section: ProjectTaskSectionUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Surface(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape),
            color = MomensTheme.colors.primary100,
            content = {},
        )
        Text(
            text = "${section.status.label} · ${section.tasks.size}",
            color = MomensTheme.colors.gray700,
            style = MomensTheme.typography.bodyB14,
        )
    }
}
