package com.momens.android.core.designsystem.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

/**
 * 글자수 카운터가 붙은 Input입니다. 카운터가 텍스트와 같은 줄, 트레일링 자리(검색
 * 아이콘이 있던 자리)에 들어가야 해서 검색 때와 마찬가지로 [MomensInput]의 내부 Row에
 * 접근할 방법이 없어 독립적으로 구현했습니다.
 */
@Composable
fun MomensCountInput(
    state: TextFieldState,
    maxLength: Int,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
) {
    val textStyle = MomensTheme.typography.bodyM14
    val contentColor = MomensTheme.colors.gray800
    val borderColor = MomensTheme.colors.gray300
    val isEmpty = state.text.isEmpty()
    val isError = state.text.length > maxLength
    val counterColor = if (isError) MomensTheme.colors.pointRed else MomensTheme.colors.gray400

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
            },
        lineLimits = lineLimits,
        textStyle = textStyle.copy(color = contentColor),
        cursorBrush = SolidColor(value = contentColor),
        decorator = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (isEmpty) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = MomensTheme.colors.gray300,
                        )
                    }
                    innerTextField()
                }

                Text(
                    text = "${state.text.length}/$maxLength",
                    style = MomensTheme.typography.bodyM12,
                    color = counterColor,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun MomensCountInputPreview() {
    MomensTheme {
        val normalState = rememberTextFieldState(initialText = "Write")
        val errorState = rememberTextFieldState(initialText = "Write")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensCountInput(
                state = normalState,
                placeholder = "Write",
                maxLength = 15,
            )

            MomensCountInput(
                state = errorState,
                placeholder = "Write",
                maxLength = 3,
            )
        }
    }
}
