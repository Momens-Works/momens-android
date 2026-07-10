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
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.momens.android.core.designsystem.theme.MomensTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TOAST_DURATION_MILLIS = 2500L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskBottomSheet(
    onDismiss: () -> Unit = {},
){

    val writeState = rememberTextFieldState(initialText = "Write")

    var selectedRole by remember { mutableStateOf<String?>(null) }
    val roles = listOf("PM", "Design", "Backend", "Frontend")

    var selectedPriority by remember { mutableStateOf<ImportantLevel?>(null) }
    val priorities = listOf(ImportantLevel.LOW, ImportantLevel.MEDIUM, ImportantLevel.HIGH)

    val isButtonEnabled by remember {
        derivedStateOf {
            writeState.text.isNotBlank() && selectedRole != null && selectedPriority != null
        }
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    var showToast by remember { mutableStateOf(false) }

    LaunchedEffect(showToast) {
        if (showToast) {
            delay(TOAST_DURATION_MILLIS)
            showToast = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
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
                    state = writeState,
                    placeholder = "",
                    maxLength = 15,
                    lineLimits = TextFieldLineLimits.SingleLine
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "역할",
                    style = MomensTheme.typography.bodyB14,
                    color = MomensTheme.colors.gray600
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    roles.forEach { role ->
                        val isSelected = selectedRole == role
                        MomensButton(
                            text = role,
                            onClick = { selectedRole = role }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "우선순위",
                    style = MomensTheme.typography.bodyB14,
                    color = MomensTheme.colors.gray600
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    priorities.forEach { level ->
                        val isSelected = selectedPriority == level
                        val tone = if (isSelected) ImportantTone.BLUE else ImportantTone.GRAY

                        MomensImportantStatus(
                            level = level,
                            tone = tone,
                            modifier = Modifier.noRippleClickable { selectedPriority = level }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                MomensCtaButton(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            onDismiss()
                            showToast = true
                        }
                    },
                    enabled = isButtonEnabled
                ) {
                    Text(
                        text = "태스크 등록",
                        color = MomensTheme.colors.white,
                        style = MomensTheme.typography.bodyB16,
                    )
                }
            }
        }

        if (showToast) {
            TaskToast(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 20.dp, vertical = 32.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TaskBottomSheetPreview(){
    MomensTheme{
        TaskBottomSheet()
    }
}

