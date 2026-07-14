package com.momens.android.presentation.project.taskedit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.momens.android.presentation.project.taskedit.viewmodel.TaskEditViewModel

@Composable
fun TaskEditRoute(
    paddingValues: PaddingValues,
    viewModel: TaskEditViewModel = hiltViewModel(),
) {
    TaskEditScreen(
        paddingValues = paddingValues
    )
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
