package com.momens.android.presentation.project.task.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskDetailRoute(
    paddingValues: PaddingValues,
) {
    TaskDetailScreen(paddingValues = paddingValues)
}

@Composable
private fun TaskDetailScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Task Detail",
        modifier = modifier.padding(paddingValues),
    )
}
