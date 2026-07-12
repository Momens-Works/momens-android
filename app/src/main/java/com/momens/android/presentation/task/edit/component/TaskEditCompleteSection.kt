package com.momens.android.presentation.task.edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.task.edit.model.CompletionIdModel
import com.momens.android.presentation.task.edit.model.CompletionRuleModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Composable
fun TaskEditCompleteSection(
    count: String,
    rules: ImmutableList<CompletionRuleModel>,
    onAddClick: () -> Unit,
    onCheckedChange: (CompletionIdModel, Boolean) -> Unit,
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
                count = count,
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
//            MomensEmptyView(
//                text = "완료기준이 등록되지 않았어요",
//                iconColor = MomensTheme.colors.gray100,
//                textColor = MomensTheme.colors.gray200,
//            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                rules.forEach { rule ->
                    key(rule.id) {
                        TaskEditCompletionRuleBox(
                            label = rule.label,
                            rule = CompletionIdModel(taskId = rule.id.taskId, itemId = rule.id.itemId),
                            isChecked = rule.isChecked,
                            onCheckedChange = { onCheckedChange(CompletionIdModel(taskId = rule.id.taskId, itemId = rule.id.itemId), rule.isChecked) },
                            onClearClick = { onClearClick(rule.id.itemId)},
                            enabled = rule.enabled,
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
        TaskEditCompleteSection(
            count = "2/4",
            modifier = Modifier.padding(10.dp),
            rules = persistentListOf(
                CompletionRuleModel(id = CompletionIdModel(taskId = "1", itemId = "2"), label = "어쩌구어쩌구 반영",   isChecked = false, enabled = false),
                CompletionRuleModel(id = CompletionIdModel(taskId = "1", itemId = "2"), label = "어쩌구어쩌구 반영", isChecked = true, enabled = true),
                CompletionRuleModel(id = CompletionIdModel(taskId = "1", itemId = "2"), label = "어쩌구어쩌구 반영", isChecked = false, enabled = true),
                CompletionRuleModel(id = CompletionIdModel(taskId = "1", itemId = "2"), label = "어쩌구어쩌구 반영", isChecked = true, enabled = true),
            ),
            onAddClick = {},
            onCheckedChange = { _, _ -> },
            onClearClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditCompleteSectionEmptyPreview() {
    MomensTheme {
        TaskEditCompleteSection(
            count = "0/0",
            modifier = Modifier.padding(10.dp),
            rules = persistentListOf(),
            onAddClick = {},
            onCheckedChange = { _, _ -> },
            onClearClick = {},
        )
    }
}
