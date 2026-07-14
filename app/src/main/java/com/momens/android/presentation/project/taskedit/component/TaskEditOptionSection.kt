package com.momens.android.presentation.project.taskedit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.button.MomensButton
import com.momens.android.core.designsystem.component.importantstatus.MomensImportantStatus
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.core.designsystem.component.type.MomensButtonType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskedit.model.TaskRole

@Composable
fun TaskEditOptionSection(
    selectedRole: TaskRole,
    selectedPriority: ImportantLevel,
    assigneeName: String,
    onRoleSelect: (TaskRole) -> Unit,
    onPrioritySelect: (ImportantLevel) -> Unit,
    onAssigneeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray100,
                shape = RoundedCornerShape(size = 8.dp),
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Text(
            text = "역할",
            style = MomensTheme.typography.bodyB12,
            color = MomensTheme.colors.gray700,
            modifier = Modifier.padding(bottom = 10.dp),
        )

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TaskRole.entries.forEach { role ->
                MomensButton(
                    text = role.label,
                    onClick = { onRoleSelect(role) },
                    type = if (role == selectedRole) {
                        MomensButtonType.PRIMARY
                    } else {
                        MomensButtonType.WHITE
                    },
                )
            }
        }

        Text(
            text = "우선순위",
            style = MomensTheme.typography.bodyB12,
            color = MomensTheme.colors.gray700,
            modifier = Modifier.padding(bottom = 10.dp),
        )

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ImportantLevel.entries.forEach { priority ->
                MomensImportantStatus(
                    level = priority,
                    tone = if (priority == selectedPriority) ImportantTone.BLUE else ImportantTone.WHITE,
                    modifier = Modifier.noRippleClickable(onClick = { onPrioritySelect(priority) }),
                )
            }
        }

        Text(
            text = "담당",
            style = MomensTheme.typography.bodyB12,
            color = MomensTheme.colors.gray700,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        MomensButton(
            text = assigneeName,
            onClick = onAssigneeClick,
            type = MomensButtonType.WHITE,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskEditOptionSectionPreview() {
    MomensTheme {
        var selectedRole by remember { mutableStateOf(TaskRole.BACKEND) }
        var selectedPriority by remember { mutableStateOf(ImportantLevel.MEDIUM) }

        Box(
            modifier = Modifier.padding(40.dp),
        ) {
            TaskEditOptionSection(
                modifier = Modifier,
                selectedRole = selectedRole,
                onRoleSelect = { selectedRole = it },
                selectedPriority = selectedPriority,
                onPrioritySelect = { selectedPriority = it },
                assigneeName = "김민지",
                onAssigneeClick = {},
            )
        }
    }
}
