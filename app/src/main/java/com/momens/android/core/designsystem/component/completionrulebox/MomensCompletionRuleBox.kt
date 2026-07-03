package com.momens.android.core.designsystem.component.completionrulebox

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensCompletionRuleBox(
    state: TextFieldState,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    val textStyle = MomensTheme.typography.bodyM12
    val contentColor = MomensTheme.colors.gray800

    BasicTextField(
        state = state,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray100,
                shape = RoundedCornerShape(8.dp),
            ),
        enabled = enabled,
        readOnly = readOnly,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = textStyle.copy(color = contentColor),
        cursorBrush = SolidColor(MomensTheme.colors.gray800),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        decorator = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .background(
                            color = MomensTheme.colors.gray400,
                            shape = RoundedCornerShape(6.dp),
                        ),
                )

                val isEmpty = state.text.isEmpty()

                Box(modifier = Modifier.weight(1f)) {
                    if (isEmpty) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = MomensTheme.colors.gray400,
                        )
                    }
                    innerTextField()
                }

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
                    contentDescription = "삭제",
                    tint = if (isEmpty) MomensTheme.colors.gray500 else contentColor,
                    modifier = Modifier
                        .size(14.dp)
                        .noRippleClickable(enabled = enabled, onClick = onClearClick),
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun MomensCompletionRuleBoxPreview() {
    MomensTheme {
        val enteredState = rememberTextFieldState(initialText = "어쩌구저쩌구 반영")
        val notEnteredState = rememberTextFieldState()

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensCompletionRuleBox(
                state = enteredState,
                placeholder = "어쩌구저쩌구 반영",
                onClearClick = {},
            )

            MomensCompletionRuleBox(
                state = notEnteredState,
                placeholder = "어쩌구저쩌구 반영",
                onClearClick = {},
            )
        }
    }
}
