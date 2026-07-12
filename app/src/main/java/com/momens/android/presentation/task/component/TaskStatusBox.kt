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
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.task.model.TaskItemData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TaskStatusBox(
    type: MomensStatusEditType,
    tasks: ImmutableList<TaskItemData>,
    onTaskClick: (TaskItemData) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        MomensSectionTitle(
            title = type.label,
            count = tasks.size.toString(),
            isEmphasized = true,
            iconRes = type.iconRes,
        )

        Spacer(modifier = Modifier.height(12.dp))

        tasks.forEachIndexed { index, task ->
            TaskListItem(
                text = task.text,
                label = task.label,
                level = task.level,
                tone = task.tone,
                count = task.count,
                onClick = { onTaskClick(task) },
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

        val dummyTasks = persistentListOf(
            TaskItemData(
                text = "text",
                label = "Android",
                count = "4",
                level = ImportantLevel.LOW,
                tone = ImportantTone.WHITE,
            ),
            TaskItemData(
                text = "text",
                label = "Android",
                count = "3",
                level = ImportantLevel.HIGH,
                tone = ImportantTone.WHITE,
            ),
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
                    tasks = dummyTasks,
                    onTaskClick = {}
                )

                Spacer(modifier = Modifier.height(20.dp))

                TaskStatusBox(
                    type = MomensStatusEditType.IN_PROGRESS,
                    tasks = persistentListOf(
                        TaskItemData(
                            text = "text",
                            label = "Android",
                            count = "2",
                            level = ImportantLevel.MEDIUM,
                            tone = ImportantTone.WHITE,
                        ),
                    ),
                    onTaskClick = {},
                )

                Spacer(modifier = Modifier.height(20.dp))

                TaskStatusBox(
                    type = MomensStatusEditType.DONE,
                    tasks = persistentListOf(),
                    onTaskClick = {},
                )
            }
        }
    }
}

