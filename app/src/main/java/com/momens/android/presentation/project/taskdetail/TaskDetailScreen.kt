package com.momens.android.presentation.project.taskdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskDetailRoute(
    paddingValues: PaddingValues,
    taskId: String,
) {
    TaskDetailScreen(
        paddingValues = paddingValues,
        taskId = taskId,
    )
}

@Composable
private fun TaskDetailScreen(
    paddingValues: PaddingValues,
    taskId: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Task Detail: $taskId",
        modifier = modifier.padding(paddingValues),
    )
}
