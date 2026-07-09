package com.momens.android.presentation.project.task.edit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.momens.android.core.designsystem.theme.MomensTheme

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

@Preview(showBackground = true)
@Composable
private fun TaskEditScreenPreview() {
    MomensTheme {
        TaskEditScreen(
            paddingValues = PaddingValues(),
        )
    }
}
