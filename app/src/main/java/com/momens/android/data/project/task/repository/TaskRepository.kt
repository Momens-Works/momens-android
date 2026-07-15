package com.momens.android.data.project.task.repository

import com.momens.android.data.project.task.model.TaskBoard
import com.momens.android.data.project.task.model.TaskCreateModel
import com.momens.android.data.project.task.model.TaskItem

interface TaskRepository {
    suspend fun getTaskBoard(projectId: String): Result<TaskBoard>

    suspend fun createTask(
        projectId: String,
        task: TaskCreateModel,
    ): Result<TaskItem>
}
