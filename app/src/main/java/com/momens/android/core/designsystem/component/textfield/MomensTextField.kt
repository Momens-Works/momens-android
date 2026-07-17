package com.momens.android.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.hideKeyboardOnFocusLost
import com.momens.android.core.designsystem.effect.momensUiShadow
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    maxLength: Int? = null,
) {
    val textStyle = MomensTheme.typography.bodyM12
    val shape = RoundedCornerShape(8.dp)
    val isError = maxLength != null && state.text.length > maxLength
    val borderColor = if (isError) MomensTheme.colors.pointRed else MomensTheme.colors.gray100
    val keyboardController = LocalSoftwareKeyboardController.current

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    var isFocused by remember { mutableStateOf(false) }
    val imeInsets = WindowInsets.ime
    val density = LocalDensity.current

    LaunchedEffect(isFocused) {
        if (isFocused) {
            snapshotFlow { state.text.toString() to imeInsets.getBottom(density) }
                .collect { bringIntoViewRequester.bringIntoView() }
        }
    }

    Column(modifier = modifier.bringIntoViewRequester(bringIntoViewRequester)) {
        BasicTextField(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .momensUiShadow(shape = shape)
                .background(
                    color = MomensTheme.colors.white,
                    shape = shape,
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = shape,
                )
                .hideKeyboardOnFocusLost(keyboardController)
                .onFocusEvent { isFocused = it.isFocused },
            inputTransformation = maxLength?.let { InputTransformation.maxLength(it) },
            lineLimits = lineLimits,
            textStyle = textStyle.copy(color = MomensTheme.colors.gray800),
            cursorBrush = SolidColor(MomensTheme.colors.gray800),
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

        if (maxLength != null) {
            Text(
                text = "${state.text.length}/$maxLength",
                style = MomensTheme.typography.bodyM12,
                color = if (isError) MomensTheme.colors.pointRed else MomensTheme.colors.gray400,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensTextFieldPreview() {
    MomensTheme {
        val notEnteredState = rememberTextFieldState()
        val enteredState = rememberTextFieldState(initialText = "text")
        val textCountState = rememberTextFieldState(initialText = "text")
        val textErrorState = rememberTextFieldState(initialText = "texttexttexttext")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensTextField(
                state = notEnteredState,
                placeholder = "text",
            )

            MomensTextField(
                state = enteredState,
                placeholder = "text",
            )

            MomensTextField(
                state = textCountState,
                placeholder = "text",
                maxLength = 15,
            )

            MomensTextField(
                state = textErrorState,
                placeholder = "text",
                maxLength = 15,
            )
        }
    }
}
