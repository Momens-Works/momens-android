package com.momens.android.presentation.project.task.edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.textfield.MomensTextField
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun TaskEditPurposeSection(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    maxLength: Int = 300,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "목적",
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.gray900,
        )

        MomensTextField(
            state = state,
            placeholder = placeholder,
            lineLimits = TextFieldLineLimits.MultiLine(),
            maxLength = maxLength,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditPurposeSectionPreview() {
    MomensTheme {
        val fakeInitialText = "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구" +
            "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구" +
            "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구"
        val fakeState = rememberTextFieldState(initialText = fakeInitialText)

        Box(
            modifier = Modifier.padding(10.dp),
        ) {
            TaskEditPurposeSection(
                state = fakeState,
                placeholder = fakeInitialText,
                modifier = Modifier,
            )
        }
    }
}
