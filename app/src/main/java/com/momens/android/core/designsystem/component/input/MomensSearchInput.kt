package com.momens.android.core.designsystem.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensSearchInput(
    state: TextFieldState,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
) {
    val textStyle = MomensTheme.typography.bodyM14
    val contentColor = MomensTheme.colors.gray800
    val borderColor = MomensTheme.colors.gray300

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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        onKeyboardAction = KeyboardActionHandler { onSearch(state.text.toString()) },
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

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = MomensTheme.colors.gray600,
                    modifier = Modifier
                        .size(20.dp)
                        .noRippleClickable(onClick = { onSearch(state.text.toString()) }),
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun MomensSearchInputPreview() {
    MomensTheme {
        val searchEmptyState = rememberTextFieldState()
        val searchFilledState = rememberTextFieldState(initialText = "Search")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensSearchInput(
                state = searchEmptyState,
                placeholder = "Search",
                onSearch = {},
            )

            MomensSearchInput(
                state = searchFilledState,
                placeholder = "Search",
                onSearch = {},
            )
        }
    }
}
