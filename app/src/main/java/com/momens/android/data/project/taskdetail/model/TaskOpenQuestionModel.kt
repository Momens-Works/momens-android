package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskOpenQuestionDto

data class TaskOpenQuestionModel(
    val id: String,
    val body: String,
)

fun TaskOpenQuestionDto.toModel(): TaskOpenQuestionModel = TaskOpenQuestionModel(
    id = id,
    body = body,
)
