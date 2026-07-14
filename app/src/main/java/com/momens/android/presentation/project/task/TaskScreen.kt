package com.momens.android.presentation.project.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.designsystem.component.button.MomensFloatingActionButton
import com.momens.android.core.designsystem.component.header.MomensDefaultHeader
import com.momens.android.core.designsystem.component.snackbar.model.MomensSnackbarModel
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.MomensSnackbarType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.core.designsystem.trigger.LocalGlobalUiEventTrigger
import com.momens.android.core.designsystem.trigger.SnackbarState
import com.momens.android.presentation.project.task.component.TaskBottomSheet
import com.momens.android.presentation.project.task.component.TaskStatusBox
import com.momens.android.presentation.project.task.component.TaskTitle
import com.momens.android.presentation.project.task.model.MomensTaskButtonType

@Composable
fun TaskRoute(
    paddingValues: PaddingValues,
    navigateToTaskDetail: (String) -> Unit,
    viewModel: TaskViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val globalUiEvent = LocalGlobalUiEventTrigger.current

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is TaskSideEffect.ShowActionSnackbar -> {
                    globalUiEvent.showSnackbar(
                        SnackbarState(
                            content = MomensSnackbarModel(
                                title = effect.message,
                                description = effect.description,
                                type = MomensSnackbarType.BUTTON,
                                onActionClick = { navigateToTaskDetail(effect.taskId) },
                            ),
                        ),
                    )
                }

                is TaskSideEffect.ShowSnackbar -> {
                    globalUiEvent.showSnackbar(
                        SnackbarState(content = MomensSnackbarModel(title = effect.message)),
                    )
                }

                is TaskSideEffect.NavigateToTaskDetail -> navigateToTaskDetail(effect.taskId)
            }
        }
    }

    TaskScreen(
        paddingValues = paddingValues,
        uiState = uiState,
        titleState = viewModel.titleState,
        onTaskClick = navigateToTaskDetail,
        onBottomSheetOpen = viewModel::onBottomSheetOpen,
        onBottomSheetDismiss = viewModel::onBottomSheetDismiss,
        onRoleSelect = viewModel::onRoleSelect,
        onPrioritySelect = viewModel::onPrioritySelect,
        onRegisterClick = viewModel::onRegisterClick,
    )
}

@Composable
private fun TaskScreen(
    paddingValues: PaddingValues,
    uiState: TaskUiState,
    titleState: TextFieldState,
    onTaskClick: (String) -> Unit,
    onBottomSheetOpen: () -> Unit,
    onBottomSheetDismiss: () -> Unit,
    onRoleSelect: (MomensTaskButtonType) -> Unit,
    onPrioritySelect: (ImportantLevel) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        Column() {
            MomensDefaultHeader(
                onProfileClick = {},
                modifier = Modifier.padding(bottom = 12.dp),
            )

            LazyColumn {
                item {
                    TaskTitle(
                        title = uiState.title,
                        description = uiState.description,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 24.dp),
                    )
                }

                items(
                    items = uiState.sections,
                    key = { it.type },
                ) { section ->
                    TaskStatusBox(
                        type = section.type,
                        tasks = section.tasks,
                        onTaskClick = { task -> onTaskClick(task.id) },
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 20.dp),
                    )
                }
            }
        }

        MomensFloatingActionButton(
            onClick = onBottomSheetOpen,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 80.dp),
        )

        if (uiState.isBottomSheetVisible) {
            TaskBottomSheet(
                titleState = titleState,
                selectedRole = uiState.selectedRole,
                selectedPriority = uiState.selectedPriority,
                onRoleSelect = onRoleSelect,
                onPrioritySelect = onPrioritySelect,
                onDismiss = onBottomSheetDismiss,
                onRegisterClick = onRegisterClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskScreenPreview() {
    MomensTheme {
        TaskScreen(
            paddingValues = PaddingValues(),
            uiState = TaskUiState.Fake,
            titleState = rememberTextFieldState(),
            onTaskClick = {},
            onBottomSheetOpen = {},
            onBottomSheetDismiss = {},
            onRoleSelect = {},
            onPrioritySelect = {},
            onRegisterClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskScreenEmptyPreview() {
    MomensTheme {
        TaskScreen(
            paddingValues = PaddingValues(),
            uiState = TaskUiState.FakeEmpty,
            titleState = rememberTextFieldState(),
            onTaskClick = {},
            onBottomSheetOpen = {},
            onBottomSheetDismiss = {},
            onRoleSelect = {},
            onPrioritySelect = {},
            onRegisterClick = {},
        )
    }
}


