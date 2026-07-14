package com.momens.android.data.project.task.remote.datasource

import com.momens.android.data.project.task.remote.dto.request.TaskCreateRequest
import com.momens.android.data.project.task.remote.dto.response.TaskBoardResponse
import com.momens.android.data.project.task.remote.dto.response.TaskCreateResponse

interface TaskRemoteDataSource {
    suspend fun getTaskBoard(projectId: String): TaskBoardResponse

    suspend fun createTask(
        projectId: String,
        request: TaskCreateRequest,
    ): TaskCreateResponse
}
