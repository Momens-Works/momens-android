package com.momens.android.presentation.project.taskdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.tag.MomensTaskTag
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme

private const val EMPTY_TITLE_TEXT = "새 태스크"

@Composable
fun TaskDetailTitleSection(
    taskDetailTitle: String,
    status: MomensStatusEditType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Text(
            text = taskDetailTitle.ifBlank { EMPTY_TITLE_TEXT },
            style = MomensTheme.typography.titleB20,
            color = MomensTheme.colors.black,
        )

        MomensTaskTag(type = status)
    }
}


@Preview(showBackground = true, heightDp = 33)
@Composable
private fun TaskDetailTitleSectionPreview() {
    MomensTheme {
        TaskDetailTitleSection(
            taskDetailTitle = "일이삼사오육칠팔일이삼사오",
            status = MomensStatusEditType.TODO,
        )
    }
}

@Preview(showBackground = true, heightDp = 33, name = "빈 상태")
@Composable
private fun TaskDetailTitleSectionEmptyPreview() {
    MomensTheme {
        TaskDetailTitleSection(
            taskDetailTitle = "",
            status = MomensStatusEditType.TODO,
        )
    }
}
