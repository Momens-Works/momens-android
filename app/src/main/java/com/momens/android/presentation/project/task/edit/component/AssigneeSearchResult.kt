package com.momens.android.presentation.project.task.edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.list.MomensPeopleListItem
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.task.edit.model.AssigneeInfo

@Composable
fun AssigneeSearchResult(
    assignees: List<AssigneeInfo>,
    onAssigneeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "검색 결과",
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.gray700, // 이후 수정
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = assignees,
                key = { it.id },
            ) { assignee ->
                MomensPeopleListItem(
                    text = assignee.name,
                    onClick = { onAssigneeClick(assignee.id) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssigneeSearchResult() {
    MomensTheme {
        AssigneeSearchResult(
            assignees = listOf(
                AssigneeInfo(id = "1", name = "강채원", url = null),
                AssigneeInfo(id = "2", name = "강채원", url = null),
                AssigneeInfo(id = "3", name = "강채원", url = null),
            ),
            onAssigneeClick = {},
        )
    }
}
