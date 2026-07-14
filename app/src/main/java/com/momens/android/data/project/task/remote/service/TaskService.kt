package com.momens.android.data.project.task.remote.service

import com.momens.android.data.project.task.remote.dto.response.TaskBoardResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskService {
    @GET("/api/mobile/projects/{projectId}/tasks")
    suspend fun getTaskBoard(
        @Path("projectId") projectId: String,
    ): TaskBoardResponse
}
