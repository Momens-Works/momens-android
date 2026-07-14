package com.momens.android.data.project.task.remote.datasource

import com.momens.android.data.project.task.remote.dto.response.TaskBoardResponse

interface TaskRemoteDataSource {
    suspend fun getTaskBoard(projectId: String): TaskBoardResponse
}
