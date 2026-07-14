package com.momens.android.data.project.task.remote.service

import com.momens.android.data.project.task.remote.dto.request.TaskCreateRequest
import com.momens.android.data.project.task.remote.dto.response.TaskBoardResponse
import com.momens.android.data.project.task.remote.dto.response.TaskCreateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskService {
    @GET("/api/mobile/projects/{projectId}/tasks")
    suspend fun getTaskBoard(
        @Path("projectId") projectId: String,
    ): TaskBoardResponse

    @POST("/api/mobile/projects/{projectId}/tasks")
    suspend fun createTask(
        @Path("projectId") projectId: String,
        @Body request: TaskCreateRequest,
    ): TaskCreateResponse
}
