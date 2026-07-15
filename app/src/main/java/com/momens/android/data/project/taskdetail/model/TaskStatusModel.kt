package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskStatusDto

enum class TaskStatusModel {
    BACKLOG,
    TODO,
    IN_PROGRESS,
    DONE,
    CANCELLED,
}

fun TaskStatusDto.toModel(): TaskStatusModel = when (this) {
    TaskStatusDto.BACKLOG -> TaskStatusModel.BACKLOG
    TaskStatusDto.TODO -> TaskStatusModel.TODO
    TaskStatusDto.IN_PROGRESS -> TaskStatusModel.IN_PROGRESS
    TaskStatusDto.DONE -> TaskStatusModel.DONE
    TaskStatusDto.CANCELLED -> TaskStatusModel.CANCELLED
}
