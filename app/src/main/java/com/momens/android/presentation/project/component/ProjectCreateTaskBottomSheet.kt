package com.momens.android.presentation.project.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCreateTaskBottomSheet(
    onDismissRequest: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var title by rememberSaveable { mutableStateOf("") }
    var selectedRole by rememberSaveable { mutableStateOf("PM") }
    var selectedPriority by rememberSaveable { mutableStateOf("중간") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        containerColor = MomensTheme.colors.white,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
        ) {
            Text(
                text = "새 태스크 생성",
                color = MomensTheme.colors.black,
                style = MomensTheme.typography.bodyB16,
            )
            Spacer(modifier = Modifier.height(24.dp))

            ProjectCreateTaskField(
                title = title,
                onTitleChange = { title = it },
            )
            Spacer(modifier = Modifier.height(18.dp))

            ProjectCreateTaskChipGroup(
                label = "역할",
                options = listOf("PM", "Design", "Backend", "Frontend"),
                selectedOption = selectedRole,
                onOptionClick = { selectedRole = it },
            )
            Spacer(modifier = Modifier.height(18.dp))

            ProjectCreateTaskChipGroup(
                label = "우선 순위",
                options = listOf("높음", "중간", "낮음"),
                selectedOption = selectedPriority,
                onOptionClick = { selectedPriority = it },
                optionPrefix = "⚡⚡",
            )
            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                enabled = title.isNotBlank(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MomensTheme.colors.primary100,
                    disabledContainerColor = MomensTheme.colors.gray300,
                    contentColor = MomensTheme.colors.white,
                    disabledContentColor = MomensTheme.colors.white,
                ),
            ) {
                Text(
                    text = "태스크 등록",
                    style = MomensTheme.typography.bodyB14,
                )
            }
        }
    }
}

@Composable
private fun ProjectCreateTaskField(
    title: String,
    onTitleChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "제목",
            color = MomensTheme.colors.gray700,
            style = MomensTheme.typography.bodyB12,
        )
        TextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Example",
                    style = MomensTheme.typography.bodyM14,
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MomensTheme.colors.white,
                unfocusedContainerColor = MomensTheme.colors.white,
                focusedIndicatorColor = MomensTheme.colors.gray300,
                unfocusedIndicatorColor = MomensTheme.colors.gray300,
            ),
        )
    }
}

@Composable
private fun ProjectCreateTaskChipGroup(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    optionPrefix: String = "",
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = MomensTheme.colors.gray700,
            style = MomensTheme.typography.bodyB12,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            options.forEach { option ->
                val text = listOf(optionPrefix, option)
                    .filter { it.isNotBlank() }
                    .joinToString(separator = " ")

                FilterChip(
                    selected = option == selectedOption,
                    onClick = { onOptionClick(option) },
                    label = {
                        Text(
                            text = text,
                            style = MomensTheme.typography.captionM11,
                        )
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MomensTheme.colors.gray100,
                        selectedContainerColor = MomensTheme.colors.primary50,
                        labelColor = MomensTheme.colors.gray700,
                        selectedLabelColor = MomensTheme.colors.white,
                    ),
                    border = null,
                )
            }
        }
    }
}
