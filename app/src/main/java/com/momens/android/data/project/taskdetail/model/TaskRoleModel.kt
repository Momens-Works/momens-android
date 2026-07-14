package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskRoleDto

enum class TaskRoleModel {
    PM,
    DESIGN,
    FRONTEND,
    BACKEND,
}

fun TaskRoleDto.toModel(): TaskRoleModel = when (this) {
    TaskRoleDto.PM -> TaskRoleModel.PM
    TaskRoleDto.DESIGN -> TaskRoleModel.DESIGN
    TaskRoleDto.FRONTEND -> TaskRoleModel.FRONTEND
    TaskRoleDto.BACKEND -> TaskRoleModel.BACKEND
}
