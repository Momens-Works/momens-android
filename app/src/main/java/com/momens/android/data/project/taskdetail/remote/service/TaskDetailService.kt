package com.momens.android.data.project.taskdetail.remote.service

import com.momens.android.data.project.taskdetail.remote.dto.request.UpdateChecklistItemRequest
import com.momens.android.data.project.taskdetail.remote.dto.response.TaskDetailResponse
import com.momens.android.data.project.taskdetail.remote.dto.response.UpdateChecklistItemResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface TaskDetailService {

    @GET("/api/mobile/tasks/{taskId}")
    suspend fun getTaskDetail(
        @Path("taskId") taskId: String,
    ): TaskDetailResponse

    @PATCH("/api/mobile/tasks/{taskId}/checklist-items/{itemId}")
    suspend fun updateChecklistItem(
        @Path("taskId") taskId: String,
        @Path("itemId") itemId: String,
        @Body request: UpdateChecklistItemRequest,
    ): UpdateChecklistItemResponse
}
