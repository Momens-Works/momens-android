package com.momens.android.presentation.task.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.bottomsheet.MomensBottomSheet
import com.momens.android.core.designsystem.component.button.MomensButton
import com.momens.android.core.designsystem.component.button.MomensCtaButton
import com.momens.android.core.designsystem.component.importantstatus.MomensImportantStatus
import com.momens.android.core.designsystem.component.input.MomensCountInput
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.core.designsystem.component.type.MomensTaskButtonType
import com.momens.android.core.designsystem.theme.MomensTheme
import kotlinx.coroutines.launch

data class TaskCreateRequest(
    val title: String,
    val role: MomensTaskButtonType,
    val priority: ImportantLevel,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskBottomSheet(

    titleState: TextFieldState,
    selectedRole: MomensTaskButtonType?,
    onRoleSelect: (MomensTaskButtonType) -> Unit,
    selectedPriority: ImportantLevel?,
    onPrioritySelect: (ImportantLevel) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    onSubmit: (TaskCreateRequest) -> Unit,
) {
    val roles = MomensTaskButtonType.entries
    val priorities = ImportantLevel.entries

    val isButtonEnabled by remember {
        derivedStateOf {
            titleState.text.isNotBlank() && selectedRole != null && selectedPriority != null
        }
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()



    Box(
        modifier = modifier,
    ) {
        MomensBottomSheet(
            onDismiss = onDismiss,
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 32.dp,
                    ),
            ) {
                Text(
                    text = "새 태스크 생성",
                    color = MomensTheme.colors.black,
                    style = MomensTheme.typography.bodyB16,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "제목",
                    color = MomensTheme.colors.gray600,
                    style = MomensTheme.typography.bodyB14,
                )

                Spacer(modifier = Modifier.height(4.dp))

                MomensCountInput(
                    state = titleState,
                    placeholder = "",
                    maxLength = 15,
                    lineLimits = TextFieldLineLimits.SingleLine,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "역할",
                    style = MomensTheme.typography.bodyB14,
                    color = MomensTheme.colors.gray600,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    roles.forEach { role ->
                        MomensButton(
                            text = role.text,
                            onClick = { onRoleSelect(role) },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "우선순위",
                    style = MomensTheme.typography.bodyB14,
                    color = MomensTheme.colors.gray600,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    priorities.forEach { level ->
                        val isSelected = selectedPriority == level
                        val tone = if (isSelected) ImportantTone.BLUE else ImportantTone.GRAY

                        MomensImportantStatus(
                            level = level,
                            tone = tone,
                            modifier = Modifier.noRippleClickable { onPrioritySelect(level) },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                MomensCtaButton(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            onSubmit(
                                TaskCreateRequest(
                                    title = titleState.text.toString(),
                                    role = selectedRole!!,
                                    priority = selectedPriority!!,
                                ),
                            )
                            onDismiss()
                        }
                    },
                    enabled = isButtonEnabled,
                ) {
                    Text(
                        text = "태스크 등록",
                        color = MomensTheme.colors.white,
                        style = MomensTheme.typography.bodyB16,
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TaskBottomSheetPreview() {
    MomensTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            val dummyTitleState = rememberTextFieldState(initialText = "Write")

            var previewSelectedRole by remember {
                mutableStateOf<MomensTaskButtonType?>(MomensTaskButtonType.entries.firstOrNull())
            }
            var previewSelectedPriority by remember {
                mutableStateOf<ImportantLevel?>(null)
            }

            TaskBottomSheet(
                titleState = dummyTitleState,
                selectedRole = previewSelectedRole,
                onRoleSelect = { previewSelectedRole = it },
                selectedPriority = previewSelectedPriority,
                onPrioritySelect = { previewSelectedPriority = it },
                onSubmit = {},
                onDismiss = {}
            )
        }
    }
}

