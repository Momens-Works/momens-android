package com.momens.android.presentation.project.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProjectTaskBoard(
    section: ProjectTaskSectionUiModel,
    onTaskClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    ProjectTaskSection(
        section = section,
        onTaskClick = onTaskClick,
        modifier = modifier,
    )
}

data class ProjectTaskSectionUiModel(
    val status: ProjectTaskStatus,
    val tasks: List<ProjectTaskUiModel>,
)

data class ProjectTaskUiModel(
    val id: Long,
    val title: String,
    val role: String,
    val priority: String,
    val materialCount: Int,
)

enum class ProjectTaskStatus(
    val label: String,
) {
    Todo(label = "투두"),
    InProgress(label = "진행중"),
    Done(label = "완료"),
}
