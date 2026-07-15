package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskPriorityDto

enum class TaskPriorityModel {
    LOW,
    MEDIUM,
    HIGH,
}

fun TaskPriorityDto.toModel(): TaskPriorityModel = when (this) {
    TaskPriorityDto.LOW -> TaskPriorityModel.LOW
    TaskPriorityDto.MEDIUM -> TaskPriorityModel.MEDIUM
    TaskPriorityDto.HIGH -> TaskPriorityModel.HIGH
}
