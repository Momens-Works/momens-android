package com.momens.android.presentation.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.component.ProjectCreateTaskBottomSheet
import com.momens.android.presentation.project.component.ProjectTaskBoard
import com.momens.android.presentation.project.component.ProjectTaskSectionUiModel
import com.momens.android.presentation.project.component.ProjectTaskStatus
import com.momens.android.presentation.project.component.ProjectTaskUiModel

@Composable
fun ProjectRoute(
    paddingValues: PaddingValues,
) {
    ProjectScreen(paddingValues = paddingValues)
}

@Composable
private fun ProjectScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var isCreateTaskSheetVisible by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 36.dp,
                end = 20.dp,
                bottom = 112.dp,
            ),
        ) {
            item {
                ProjectHeader()
                Spacer(modifier = Modifier.height(28.dp))
            }

            items(projectTaskSections) { section ->
                ProjectTaskBoard(
                    section = section,
                    onTaskClick = {},
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        FloatingActionButton(
            onClick = { isCreateTaskSheetVisible = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = MomensTheme.colors.primary100,
            contentColor = MomensTheme.colors.white,
        ) {
            Text(
                text = "+",
                style = MomensTheme.typography.titleB24,
            )
        }
    }

    if (isCreateTaskSheetVisible) {
        ProjectCreateTaskBottomSheet(
            onDismissRequest = { isCreateTaskSheetVisible = false },
            onRegisterClick = { isCreateTaskSheetVisible = false },
        )
    }
}

@Composable
private fun ProjectHeader(
    modifier: Modifier = Modifier,
) {
    androidx.compose.foundation.layout.Column(modifier = modifier) {
        Text(
            text = "프로젝트 태스크",
            color = MomensTheme.colors.black,
            style = MomensTheme.typography.titleB20,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "업무를 한눈에 확인하고 상세 내용을 확인하세요.",
            color = MomensTheme.colors.gray500,
            style = MomensTheme.typography.bodyM12,
        )
    }
}

private val projectTaskSections = listOf(
    ProjectTaskSectionUiModel(
        status = ProjectTaskStatus.Todo,
        tasks = listOf(
            ProjectTaskUiModel(id = 1, title = "text", role = "Android", priority = "낮음", materialCount = 2),
            ProjectTaskUiModel(id = 2, title = "text", role = "Android", priority = "낮음", materialCount = 2),
        ),
    ),
    ProjectTaskSectionUiModel(
        status = ProjectTaskStatus.InProgress,
        tasks = listOf(
            ProjectTaskUiModel(id = 3, title = "text", role = "Android", priority = "낮음", materialCount = 2),
            ProjectTaskUiModel(id = 4, title = "text", role = "Android", priority = "낮음", materialCount = 2),
        ),
    ),
    ProjectTaskSectionUiModel(
        status = ProjectTaskStatus.Done,
        tasks = listOf(
            ProjectTaskUiModel(id = 5, title = "text", role = "Android", priority = "낮음", materialCount = 2),
        ),
    ),
)
