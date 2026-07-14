package com.momens.android.data.project.task.remote.datasourceimpl

import com.momens.android.data.project.task.remote.datasource.TaskRemoteDataSource
import com.momens.android.data.project.task.remote.dto.response.TaskBoardResponse
import com.momens.android.data.project.task.remote.service.TaskService
import jakarta.inject.Inject

class TaskRemoteDataSourceImpl @Inject constructor(
    private val taskService: TaskService,
) : TaskRemoteDataSource {
    override suspend fun getTaskBoard(projectId: String): TaskBoardResponse =
        taskService.getTaskBoard(projectId)
}
