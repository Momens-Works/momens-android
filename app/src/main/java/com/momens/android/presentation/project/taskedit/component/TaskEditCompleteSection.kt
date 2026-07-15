package com.momens.android.presentation.project.taskedit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.emptyview.MomensEmptyView
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskedit.model.ChecklistItemState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Composable
fun TaskEditCompleteSection(
    completedCount: Int,
    totalCount: Int,
    maxLength: Int,
    rules: ImmutableList<ChecklistItemState>,
    onAddClick: () -> Unit,
    onTitleChange: (String, String) -> Unit,
    onCheckedChange: (String, Boolean) -> Unit,
    onClearClick: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.fillMaxWidth(),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            MomensSectionTitle(
                title = "완료기준",
                count = "${completedCount}/${totalCount}",
                isEmphasized = true,
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.noRippleClickable(onClick = onAddClick),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = MomensTheme.colors.black,
                    modifier = Modifier.size(16.dp)

                )

                Text(
                    text = "기준 추가",
                    style = MomensTheme.typography.bodyM14,
                    color = MomensTheme.colors.gray800
                )
            }
        }

        if (rules.isEmpty()) {
            MomensEmptyView(
                text = "완료기준이 등록되지 않았어요",
                iconColor = MomensTheme.colors.gray100,
                textColor = MomensTheme.colors.gray200,
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                rules.forEach { rule ->
                    key(rule.localId) {
                        TaskEditCompletionRuleBox(
                            rule = rule,
                            maxLength = maxLength,
                            onTitleChange = onTitleChange,
                            onCheckedChange = { localId, checked -> onCheckedChange(localId, checked) },
                            onClearClick = { onClearClick(rule.localId) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditCompleteSectionPreview() {
    MomensTheme {
        val exampleState = rememberTextFieldState()
        val writeState = rememberTextFieldState(initialText = "어쩌구저쩌구")

        TaskEditCompleteSection(
            completedCount = 2,
            totalCount = 4,
            modifier = Modifier.padding(10.dp),
            rules = persistentListOf(
                ChecklistItemState(id = "1", localId = "1", title = exampleState.text.toString(), completed = false),
                ChecklistItemState(id = "2", localId = "2", title = exampleState.text.toString(), completed = true),
                ChecklistItemState(id = "3", localId = "3", title = writeState.text.toString(), completed = false),
                ChecklistItemState(id = "4", localId = "4", title = writeState.text.toString(), completed = true),
            ),
            onAddClick = {},
            onCheckedChange = { _, _ -> },
            onClearClick = {},
            maxLength = 50,
            onTitleChange = { _, _ -> },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditCompleteSectionEmptyPreview() {
    MomensTheme {
        TaskEditCompleteSection(
            completedCount = 0,
            totalCount = 0,
            modifier = Modifier.padding(10.dp),
            rules = persistentListOf(),
            onAddClick = {},
            onCheckedChange = { _, _ -> },
            onTitleChange = { _, _ -> },
            maxLength = 50,
            onClearClick = {},
        )
    }
}
