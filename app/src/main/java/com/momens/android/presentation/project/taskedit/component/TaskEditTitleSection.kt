package com.momens.android.presentation.project.taskedit.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.input.MomensCountInput
import com.momens.android.core.designsystem.component.tag.MomensTaskTag
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme


@Composable
fun TaskEditTitleSection(
    titleState: TextFieldState,
    status: MomensStatusEditType,
    modifier: Modifier = Modifier,
    maxLength: Int = 15,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MomensCountInput(
            state = titleState,
            placeholder = "태스크 제목을 입력해주세요",
            maxLength = maxLength,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
        )

        MomensTaskTag(
            type = status,
            modifier = Modifier
                .padding(vertical = 4.dp),
        )
    }
}


@Preview(showBackground = true, heightDp = 33)
@Composable
private fun TaskEditTitleSectionPreview() {
    MomensTheme {
        val normalState = rememberTextFieldState(initialText = "1차 와이어프레임")

        TaskEditTitleSection(
            titleState = normalState,
            status = MomensStatusEditType.TODO,
        )
    }
}
