package com.momens.android.data.project.taskedit.remote.service

import com.momens.android.data.project.taskedit.remote.dto.request.TaskEditRequestDto
import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMembersResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TaskEditService {
    @PATCH("/api/mobile/tasks/{taskId}")
    suspend fun patchTaskEdit(
        @Path("taskId") taskId: String,
        @Body request: TaskEditRequestDto,
    ): Response<Unit>

    @GET("api/mobile/projects/{projectId}/members")
    suspend fun getTaskEditMembers(
        @Path("projectId") projectId: String,
        @Query("query") query: String?,
    ): TaskEditMembersResponseDto
}
