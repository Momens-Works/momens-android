package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.project.taskdetail.model.TaskOpenQuestionModel

@Immutable
data class TaskDetailQuestionModel(
    val id: String,
    val body: String,
)

fun TaskOpenQuestionModel.toUiModel(): TaskDetailQuestionModel = TaskDetailQuestionModel(
    id = id,
    body = body,
)
