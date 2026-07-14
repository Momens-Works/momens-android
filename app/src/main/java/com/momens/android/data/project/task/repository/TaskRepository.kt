package com.momens.android.data.project.task.repository

import com.momens.android.data.project.task.model.TaskBoard
import com.momens.android.data.project.task.model.TaskItem

interface TaskRepository {
    suspend fun getTaskBoard(projectId: String): Result<TaskBoard>

    suspend fun createTask(
        projectId: String,
        title: String,
        role: String,
        priority: String,
    ): Result<TaskItem>
}
