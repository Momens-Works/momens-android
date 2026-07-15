package com.momens.android.presentation.project.taskedit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.common.extension.noRippleToggleable
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskedit.model.ChecklistItemState

@Composable
fun TaskEditCompletionRuleBox(
    rule: ChecklistItemState,
    onTitleChange: (String, String) -> Unit,
    onCheckedChange: (String, Boolean) -> Unit,
    onClearClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val titleState = remember(rule.localId) { TextFieldState(rule.title) }

    LaunchedEffect(titleState) {
        snapshotFlow { titleState.text.toString() }
            .collect { text ->
                onTitleChange(rule.localId, text)
            }
    }

    val iconRes = if (rule.completed) R.drawable.ic_checkbox_fill else R.drawable.ic_checkbox_empty
    val iconTint = if (rule.completed) MomensTheme.colors.primary50 else MomensTheme.colors.gray300

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray100,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(18.dp)
                .background(
                    color = MomensTheme.colors.gray200,
                    shape = RoundedCornerShape(6.dp),
                ),
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .noRippleToggleable(
                    value = rule.completed,
                    role = Role.Checkbox,
                    onValueChange = { checked -> onCheckedChange(rule.localId, checked) },
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconRes),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp),
            )

            BasicTextField(
                state = titleState,
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MomensTheme.typography.bodyM12,
                cursorBrush = SolidColor(value = MomensTheme.colors.gray800),
                decorator = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (rule.title.isEmpty()) {
                                Text(
                                    text = "완료기준을 입력해주세요.",
                                    style = MomensTheme.typography.bodyM12,
                                    color = MomensTheme.colors.gray300,
                                )
                            }
                            innerTextField()
                        }
                    }
                },
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
            contentDescription = null,
            tint = MomensTheme.colors.gray800,
            modifier = Modifier
                .size(14.dp)
                .noRippleClickable(
                    onClick = { onClearClick(rule.localId) },
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditCompletionRuleBoxPreview() {
    MomensTheme {
        val exampleState = rememberTextFieldState()
        val writeState = rememberTextFieldState(initialText = "어쩌구저쩌구")

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TaskEditCompletionRuleBox(
                rule = ChecklistItemState(id = "1", localId = "1", title = writeState.text.toString(), completed = true),
                onCheckedChange = { _, _ -> },
                onClearClick = {},
                onTitleChange = { _, _ -> }
            )

            TaskEditCompletionRuleBox(
                rule = ChecklistItemState(id = "2", localId = "2", title = exampleState.text.toString(), completed = false),
                onCheckedChange = { _, _ -> },
                onClearClick = {},
                onTitleChange = { _, _ -> }
            )
        }
    }
}
