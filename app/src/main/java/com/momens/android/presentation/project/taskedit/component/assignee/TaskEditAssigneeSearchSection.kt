package com.momens.android.presentation.project.taskedit.component.assignee

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
import com.momens.android.presentation.project.model.Assignee
import com.momens.android.presentation.project.taskedit.component.TaskEditPeopleListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TaskEditAssigneeSearchSection(
    assignees: ImmutableList<Assignee>,
    selectedAssignee: Assignee?,
    onAssigneeClick: (Assignee) -> Unit,
    onDeleteClick: () -> Unit,
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
            if (assignees.isNotEmpty()){
                items(
                    items = assignees,
                    key = { it.id }
                ){ assignee ->
                    TaskEditPeopleListItem(
                        text = assignee.name,
                        onClick = { onAssigneeClick(assignee) },
                        isSelected = assignee.id == selectedAssignee?.id,
                        onDeleteClick = onDeleteClick,
                        profileImageUrl = assignee.url,
                    )
                }
            }
            else {
                item{
                    Text(
                        text = "검색결과가 없습니다.",
                        style = MomensTheme.typography.bodyM14,
                        color = MomensTheme.colors.gray300
                    )
                }

                item{
                    Spacer(modifier = Modifier.height(178.dp))
                }
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
                Assignee(
                    id = "1",
                    name = "강채원",
                    url = null
                ),
                Assignee(
                    id = "2",
                    name = "강채원",
                    url = null
                ),
                Assignee(
                    id = "3",
                    name = "강채원",
                    url = null
                ),
            ),
            selectedAssignee = null,
            onAssigneeClick = {},
            onDeleteClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditAssigneeSearchSectionEmptyPreview() {
    MomensTheme {
        TaskEditAssigneeSearchSection(
            assignees = persistentListOf(),
            selectedAssignee = null,
            onAssigneeClick = {},
            onDeleteClick = {},
        )
    }
}
