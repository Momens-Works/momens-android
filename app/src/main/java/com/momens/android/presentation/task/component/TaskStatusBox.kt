package com.momens.android.presentation.task.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.sectiontitle.MomensSectionTitle
import com.momens.android.core.designsystem.component.tasklist.MomensTaskListItem
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.core.designsystem.type.MomensTaskType

@Composable
fun TaskStatusBox(
    type: MomensTaskType,
    count: Int,
    text: String,
    label: String,
    level: ImportantLevel,
    tone: ImportantTone,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MomensSectionTitle(
            title = type.label,
            count = count,
            isEmphasized = true,
            iconRes = type.iconRes,
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomensTaskListItem(
            text = text,
            label = label,
            level = level,
            tone = tone,
            count = count,
            onClick = {},
        )

        Spacer(modifier = Modifier.height(8.dp))

        MomensTaskListItem(
            text = text,
            label = label,
            level = level,
            tone = tone,
            count = count,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskStatusBoxPreview() {
    MomensTheme {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp,
                ),
        ) {
            Column {
                TaskStatusBox(
                    type = MomensTaskType.TODO,
                    count = 2,
                    text = "text",
                    label = "Android",
                    level = ImportantLevel.LOW,
                    tone = ImportantTone.GRAY,
                )

                Spacer(modifier = Modifier.height(20.dp))

                TaskStatusBox(
                    type = MomensTaskType.IN_PROGRESS,
                    count = 2,
                    text = "text",
                    label = "Android",
                    level = ImportantLevel.LOW,
                    tone = ImportantTone.GRAY,
                )

                Spacer(modifier = Modifier.height(20.dp))

                TaskStatusBox(
                    type = MomensTaskType.DONE,
                    count = 2,
                    text = "text",
                    label = "Android",
                    level = ImportantLevel.LOW,
                    tone = ImportantTone.GRAY,
                )
            }
        }
    }
}
