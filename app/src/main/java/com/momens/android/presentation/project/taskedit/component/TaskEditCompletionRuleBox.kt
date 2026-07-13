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
import com.momens.android.presentation.project.taskedit.model.CompletionIdModel

@Composable
fun TaskEditCompletionRuleBox(
    rule: CompletionIdModel,
    isChecked: Boolean,
    state: TextFieldState,
    onCheckedChange: (CompletionIdModel, Boolean) -> Unit,
    onClearClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconRes = if (isChecked) R.drawable.ic_checkbox_fill else R.drawable.ic_checkbox_empty
    val iconTint = if (isChecked) MomensTheme.colors.primary50 else MomensTheme.colors.gray300

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
                    value = isChecked,
                    role = Role.Checkbox,
                    onValueChange = { checked -> onCheckedChange(rule, checked) },
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
                state = state,
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
                            if (state.text.isEmpty()) {
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
                    onClick = { onClearClick(rule.itemId) },
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
                state = writeState,
                rule = CompletionIdModel(taskId = "1", itemId = "1"),
                isChecked = true,
                onCheckedChange = { _, _ -> },
                onClearClick = {},
            )

            TaskEditCompletionRuleBox(
                state = exampleState,
                rule = CompletionIdModel(taskId = "1", itemId = "2"),
                isChecked = false,
                onCheckedChange = { _, _ -> },
                onClearClick = {},
            )
        }
    }
}
