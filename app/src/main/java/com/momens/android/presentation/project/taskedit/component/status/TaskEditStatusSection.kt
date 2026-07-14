package com.momens.android.presentation.project.taskedit.component.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskedit.component.TaskEditStatusEdit

@Composable
fun TaskEditStatusSection(
    status: MomensStatusEditType,
    onStatusChange: (MomensStatusEditType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = "진행사항 수정",
            modifier = Modifier.padding(bottom = 20.dp),
            color = MomensTheme.colors.black,
            style = MomensTheme.typography.bodyB16,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MomensStatusEditType.entries.forEach { type ->
                TaskEditStatusEdit(
                    type = type,
                    modifier = Modifier.noRippleClickable(onClick = { onStatusChange(type) }),
                    isSelected = status == type,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditStatusSectionPreview() {
    MomensTheme {
        Box(
            modifier = Modifier.padding(10.dp),
        ) {
            TaskEditStatusSection(
                status = MomensStatusEditType.TODO,
                onStatusChange = {},
            )
        }
    }
}
