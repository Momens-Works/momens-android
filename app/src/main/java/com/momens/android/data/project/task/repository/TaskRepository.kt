package com.momens.android.data.project.task.repository

import com.momens.android.data.project.task.model.TaskBoard

interface TaskRepository {
    suspend fun getTaskBoard(projectId: String): Result<TaskBoard>
}
