package com.momens.android.presentation.task.edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.task.edit.model.AssigneeInfo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TaskEditAssigneeSearchSection(
    assignees: ImmutableList<AssigneeInfo>,
    onClick: () -> Unit,
    onAssigneeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = "검색 결과",
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.gray700,
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = assignees,
                key = { it.id }
            ){ assignee ->
                TaskEditPeopleListItem(
                    text = assignee.name,
                    onClick = onClick,
                    onDeleteClick = { onAssigneeClick(assignee.id) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditAssigneeSearchSectionPreview() {
    MomensTheme {
        TaskEditAssigneeSearchSection(
            assignees = persistentListOf(
                AssigneeInfo(id = "1", name = "강채원", url = null),
                AssigneeInfo(id = "2", name = "강채원", url = null),
                AssigneeInfo(id = "3", name = "강채원", url = null),
            ),
            onClick = {},
            onAssigneeClick = {},
        )
    }
}
