package com.momens.android.presentation.project.taskedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.designsystem.component.header.MomensHeader
import com.momens.android.core.designsystem.component.snackbar.model.MomensSnackbarModel
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.core.designsystem.trigger.LocalGlobalUiEventTrigger
import com.momens.android.core.designsystem.trigger.SnackbarState
import com.momens.android.presentation.project.taskedit.component.TaskEditCompleteSection
import com.momens.android.presentation.project.taskedit.component.TaskEditOptionSection
import com.momens.android.presentation.project.taskedit.component.TaskEditPurposeSection
import com.momens.android.presentation.project.taskedit.component.TaskEditTitleSection
import com.momens.android.presentation.project.taskedit.component.assignee.TaskEditAssigneeBottomSheet
import com.momens.android.presentation.project.taskedit.component.status.TaskEditStatusBottomSheet
import com.momens.android.presentation.project.taskedit.model.Assignee
import com.momens.android.presentation.project.taskedit.model.TaskRole
import com.momens.android.presentation.project.taskedit.state.TaskEditSideEffect
import com.momens.android.presentation.project.taskedit.state.TaskEditState

@Composable
fun TaskEditRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: TaskEditViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val globalUiEvent = LocalGlobalUiEventTrigger.current

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is TaskEditSideEffect.ShowSnackBar -> {
                    globalUiEvent.showSnackbar(
                        SnackbarState(content = MomensSnackbarModel(title = effect.message)),
                    )
                }

                is TaskEditSideEffect.NavigateUp -> navigateUp()
            }
        }
    }

    TaskEditScreen(
        paddingValues = paddingValues,
        state = state,
        onStatusChange = viewModel::changeStatus,
        onRoleSelect = viewModel::changeRole,
        onPriorityChange = viewModel::changePriority,
        onAssigneeChange = viewModel::updateAssignee,
        onAssigneeSearchClick = viewModel::getAssignees,
        onChecklistTitleChange = viewModel::updateChecklistTitle,
        onChecklistAddClick = viewModel::addChecklistItem,
        onCheckedChange = viewModel::changeCheck,
        onChecklistClearClick = viewModel::clearChecklistItem,
        onSaveClick = viewModel::saveTask,
        onAssigneeDeleteClick = viewModel::removeAssignee,
    )
}

@Composable
private fun TaskEditScreen(
    state: TaskEditState,
    onStatusChange: (MomensStatusEditType) -> Unit,
    onRoleSelect: (TaskRole) -> Unit,
    onPriorityChange: (ImportantLevel) -> Unit,
    onAssigneeChange: (Assignee) -> Unit,
    onAssigneeSearchClick: (String) -> Unit,
    onChecklistAddClick: () -> Unit,
    onCheckedChange: (String, Boolean) -> Unit,
    onChecklistTitleChange: (String, String) -> Unit,
    onChecklistClearClick: (String) -> Unit,
    onSaveClick: (String, String) -> Unit,
    onAssigneeDeleteClick: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var isAssigneeClicked by remember { mutableStateOf(value = false) }
    var isStatusClicked by remember { mutableStateOf(value = false) }

    val task = state.task

    val titleState = remember { TextFieldState(task.titleState) }
    val purposeState = remember { TextFieldState(task.purposeState) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        MomensHeader(
            text = state.pageTitle,
            onBackClick = { onSaveClick(titleState.text.toString(), purposeState.text.toString()) },
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(color = MomensTheme.colors.white)
                .padding(horizontal = 20.dp),
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                TaskEditTitleSection(
                    titleState = titleState,
                    status = task.status,
                    maxLength = 15,
                    onStatusClick = { isStatusClicked = true },
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                TaskEditOptionSection(
                    selectedRole = task.role,
                    selectedPriority = task.priority,
                    assigneeName = task.assignee?.name ?: "미지정",
                    onRoleSelect = onRoleSelect,
                    onPrioritySelect = onPriorityChange,
                    onAssigneeClick = { isAssigneeClicked = true },
                    modifier = Modifier,
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                TaskEditPurposeSection(
                    state = purposeState,
                    placeholder = "목적을 입력해주세요.",
                    modifier = Modifier,
                    maxLength = 300,
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                TaskEditCompleteSection(
                    completedCount = task.checklistCompletedCount,
                    totalCount = task.checklistTotalCount,
                    rules = task.checklist,
                    onAddClick = onChecklistAddClick,
                    onTitleChange = onChecklistTitleChange,
                    onCheckedChange = onCheckedChange,
                    onClearClick = onChecklistClearClick,
                    modifier = Modifier,
                )
            }

            item { Spacer(modifier = Modifier.height(6.dp)) }
        }

    }


    if (isStatusClicked) {
        TaskEditStatusBottomSheet(
            status = task.status,
            onStatusChange = onStatusChange,
            onDismiss = { isStatusClicked = false },
        )
    }

    if (isAssigneeClicked) {
        val searchState = remember { TextFieldState() }

        TaskEditAssigneeBottomSheet(
            state = searchState,
            selectedAssignee = task.assignee,
            assignees = state.assignees,
            onDismiss = { isAssigneeClicked = false },
            onAssigneeChange = onAssigneeChange,
            onDeleteClick = onAssigneeDeleteClick,
            onSearchClick = onAssigneeSearchClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditScreenPreview() {
    MomensTheme {
        var state by remember { mutableStateOf(TaskEditState.Fake) }

        TaskEditScreen(
            paddingValues = PaddingValues(0.dp),
            state = state,
            onStatusChange = {},
            onRoleSelect = {},
            onPriorityChange = {},
            onAssigneeChange = {},
            onChecklistAddClick = {},
            onCheckedChange = { _, _ -> },
            onChecklistClearClick = {},
            onSaveClick = { _, _ -> },
            onAssigneeDeleteClick = {},
            onAssigneeSearchClick = {},
            onChecklistTitleChange = { _, _ -> },
        )
    }
}
