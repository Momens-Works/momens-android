package com.momens.android.presentation.project.task

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskRoute(
    paddingValues: PaddingValues,
) {
    TaskScreen(paddingValues = paddingValues)
}

@Composable
private fun TaskScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Task",
        modifier = modifier.padding(paddingValues),
    )
}
