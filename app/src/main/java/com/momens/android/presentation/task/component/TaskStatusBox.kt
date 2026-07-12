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
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme

data class TaskItemData(
    val text: String,
    val label: String,
    val level: ImportantLevel,
    val tone: ImportantTone
)
@Composable
fun TaskStatusBox(
    type: MomensStatusEditType,
    tasks: List<TaskItemData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MomensSectionTitle(
            title = type.label,
            count = tasks.size,
            isEmphasized = true,
            iconRes = type.iconRes,
        )

        Spacer(modifier = Modifier.height(12.dp))

        tasks.forEachIndexed { index, task ->
            MomensTaskListItem(
                text = task.text,
                label = task.label,
                level = task.level,
                tone = task.tone,
                count = tasks.size,
                onClick = {},
            )

            if (index < tasks.lastIndex) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    private fun TaskStatusBoxPreview() {
        MomensTheme {

            val dummyTasks = listOf(
                TaskItemData(
                    text = "text",
                    label = "Android",
                    level = ImportantLevel.LOW,
                    tone = ImportantTone.CLEAR
                ),
                TaskItemData(
                    text = "text",
                    label = "Android",
                    level = ImportantLevel.HIGH,
                    tone = ImportantTone.CLEAR
                )
            )

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp,
                    ),
            ) {
                Column {
                    TaskStatusBox(
                        type = MomensStatusEditType.TODO,
                        tasks = dummyTasks
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TaskStatusBox(
                        type = MomensStatusEditType.IN_PROGRESS,
                        tasks = listOf(
                            TaskItemData(
                                text = "text",
                                label = "Android",
                                level = ImportantLevel.MEDIUM,
                                tone = ImportantTone.CLEAR
                            )
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TaskStatusBox(
                        type = MomensStatusEditType.DONE,
                        tasks = emptyList()
                    )
                }
            }
        }
    }

