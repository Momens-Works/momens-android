package com.momens.android.presentation.task.edit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskEditRoute(
    paddingValues: PaddingValues,
) {
    TaskEditScreen(paddingValues = paddingValues)
}

@Composable
private fun TaskEditScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Task Edit",
        modifier = modifier.padding(paddingValues),
    )
}
