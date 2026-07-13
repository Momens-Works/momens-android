package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.presentation.project.taskedit.model.TaskRole
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class TaskDetailModel(
    val id: String,
    val projectId: String,
    val title: String,
    val status: MomensStatusEditType,
    val role: TaskRole,
    val assignee: TaskDetailAssigneeModel?,
    val priority: ImportantLevel,
    val purpose: String?,
    val checklist: TaskDetailChecklistModel,
    val materials: ImmutableList<TaskDetailFileModel>,
    val openQuestions: ImmutableList<TaskDetailQuestionModel>,
    val nextAction: String?,
)
