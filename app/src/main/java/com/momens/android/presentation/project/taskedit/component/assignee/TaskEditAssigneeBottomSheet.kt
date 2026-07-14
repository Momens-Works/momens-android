package com.momens.android.presentation.project.taskedit.component.assignee

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.bottomsheet.MomensBottomSheet
import com.momens.android.core.designsystem.component.input.MomensSearchInput
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskedit.model.Assignee
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditAssigneeBottomSheet(
    state: TextFieldState,
    selectedAssignee: Assignee?,
    assignees: ImmutableList<Assignee>,
    onDismiss: () -> Unit,
    onAssigneeChange: (Assignee) -> Unit,
    onDeleteClick: () -> Unit,
    onSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MomensBottomSheet(
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        var isSearched by remember { mutableStateOf(value = false) }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp),
        ) {
            Text(
                text = "담당자 수정",
                style = MomensTheme.typography.bodyB16,
                color = MomensTheme.colors.black,
            )

            Spacer(modifier = Modifier.height(20.dp))

            MomensSearchInput(
                state = state,
                onSearch = {
                    isSearched = true
                    onSearchClick(state.text.toString())
                },
                placeholder = "Search",
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isSearched) {
                TaskEditAssigneeSearchSection(
                    assignees = assignees,
                    selectedAssignee = selectedAssignee,
                    onAssigneeClick = onAssigneeChange,
                    onDeleteClick = onDeleteClick,
                )
            } else {
                TaskEditAssigneeSection(
                    assignee = selectedAssignee,
                    onDeleteClick = onDeleteClick,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TaskEditAssigneeBottomSheetSearchPreview() {
    MomensTheme {
        val previewAssignees = persistentListOf(
            Assignee(id = "1", name = "강채원", url = null),
            Assignee(id = "2", name = "김민지", url = null),
            Assignee(id = "3", name = "이서준", url = null),
        )

        Box(modifier = Modifier.fillMaxSize()) {
            TaskEditAssigneeBottomSheet(
                state = rememberTextFieldState(),
                selectedAssignee = previewAssignees.first(),
                assignees = previewAssignees,
                onDismiss = {},
                onAssigneeChange = {},
                onDeleteClick = {},
                onSearchClick = {},
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TaskEditAssigneeBottomSheetSelectedPreview() {
    MomensTheme {
        val previewAssignee = Assignee(id = "1", name = "강채원", url = null)

        Box(modifier = Modifier.fillMaxSize()) {
            TaskEditAssigneeBottomSheet(
                state = rememberTextFieldState(),
                selectedAssignee = previewAssignee,
                assignees = persistentListOf(),
                onDismiss = {},
                onAssigneeChange = {},
                onDeleteClick = {},
                onSearchClick = {},
            )
        }
    }
}
