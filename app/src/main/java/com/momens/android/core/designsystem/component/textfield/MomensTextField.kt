package com.momens.android.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.effect.momensUiShadow
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    val textStyle = MomensTheme.typography.bodyM12
    val shape = RoundedCornerShape(8.dp)

    BasicTextField(
        state = state,
        modifier = modifier
            .fillMaxWidth()
            .momensUiShadow(shape = shape)
            .background(
                color = MomensTheme.colors.white,
                shape = shape,
            )
            .border(
                width = 1.dp,
                color = MomensTheme.colors.gray100,
                shape = shape,
            ),
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = inputTransformation,
        outputTransformation = outputTransformation,
        lineLimits = lineLimits,
        textStyle = textStyle.copy(color = MomensTheme.colors.gray800),
        cursorBrush = SolidColor(MomensTheme.colors.gray800),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        decorator = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                if (state.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = textStyle.copy(color = MomensTheme.colors.gray300),
                    )
                }
                innerTextField()
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun MomensTextFieldPreview() {
    MomensTheme {
        val singleLineState = rememberTextFieldState()
        val multiLineState = rememberTextFieldState(
            initialText = "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구" +
                "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구" +
                "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구" +
                "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구" +
                "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구" +
                "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구",
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensTextField(
                state = singleLineState,
                placeholder = "입력해주세요",
                lineLimits = TextFieldLineLimits.SingleLine,
            )

            MomensTextField(
                state = multiLineState,
                placeholder = "입력해주세요",
                lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 6),
            )
        }
    }
}
