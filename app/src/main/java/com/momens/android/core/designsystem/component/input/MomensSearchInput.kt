package com.momens.android.core.designsystem.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
) {
    MomensInput(
        state = state,
        modifier = modifier,
        placeholder = placeholder,
        lineLimits = lineLimits,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        onKeyboardAction = KeyboardActionHandler { onSearch() },
        trailingContent = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = MomensTheme.colors.gray600,
                modifier = Modifier
                    .size(20.dp)
                    .noRippleClickable(onClick = onSearch),
            )
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
