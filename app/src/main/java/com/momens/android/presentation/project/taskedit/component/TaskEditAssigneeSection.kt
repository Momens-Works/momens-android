package com.momens.android.presentation.project.taskedit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskedit.model.AssigneeInfo


@Composable
fun TaskEditAssigneeSection(
    assignee: AssigneeInfo?,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = "현재 담당자",
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.gray700,
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (assignee != null) {
            TaskEditPeopleListItem(
                text = assignee.name,
                onClick = { onDeleteClick(assignee.id) },
                onDeleteClick = { onDeleteClick(assignee.id) },
                modifier = Modifier.padding(bottom = 16.dp),
                isSelected = true,
            )
        } else{
            Text(
                text = "현재 담당자가 지정되어있지 않습니다.",
                style = MomensTheme.typography.bodyM14,
                color = MomensTheme.colors.gray300,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditAssigneeSectionPreview(){
    MomensTheme{
        Column(
            modifier = Modifier.padding(all = 20.dp)
        ){
            TaskEditAssigneeSection(
                assignee = AssigneeInfo(
                    id = "1",
                    name = "강채원",
                    url = null
                ),
                onDeleteClick = {},
            )

            Spacer(modifier = Modifier.height(20.dp))

            TaskEditAssigneeSection(
                assignee = null,
                onDeleteClick = {},
            )
        }

    }
}
