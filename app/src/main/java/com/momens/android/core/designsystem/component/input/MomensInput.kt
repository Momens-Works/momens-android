package com.momens.android.core.designsystem.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.hideKeyboardOnFocusLost
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensInput(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    maxLength: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    trailingContent: @Composable () -> Unit = {},
) {
    val textStyle = MomensTheme.typography.bodyM14
    val contentColor = MomensTheme.colors.gray800
    val borderColor = MomensTheme.colors.gray300
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        state = state,
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx(),
                )
            }
            .hideKeyboardOnFocusLost(keyboardController),
        inputTransformation = maxLength?.let { InputTransformation.maxLength(it) },
        lineLimits = lineLimits,
        textStyle = textStyle.copy(color = contentColor),
        cursorBrush = SolidColor(value = contentColor),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        decorator = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (state.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = MomensTheme.colors.gray300,
                        )
                    }
                    innerTextField()
                }

                trailingContent()
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun MomensInputPreview() {
    MomensTheme {
        val exampleState = rememberTextFieldState()
        val writeState = rememberTextFieldState(initialText = "Write")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensInput(
                state = exampleState,
                placeholder = "Example",
                maxLength = 6,
            )

            MomensInput(
                state = writeState,
                placeholder = "Example",
            )
        }
    }
}
