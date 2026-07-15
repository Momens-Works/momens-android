package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.common.extension.limitLength
import com.momens.android.data.project.taskdetail.model.TaskOpenQuestionModel

@Immutable
data class TaskDetailQuestionModel(
    val id: String,
    val body: String,
)

private const val BODY_MAX_LENGTH = 50

fun TaskOpenQuestionModel.toUiModel(): TaskDetailQuestionModel = TaskDetailQuestionModel(
    id = id,
    body = body.limitLength(BODY_MAX_LENGTH),
)
