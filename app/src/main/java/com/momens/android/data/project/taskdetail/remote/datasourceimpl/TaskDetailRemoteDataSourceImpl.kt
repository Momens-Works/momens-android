package com.momens.android.data.project.taskdetail.remote.datasourceimpl

import com.momens.android.data.project.taskdetail.remote.datasource.TaskDetailRemoteDataSource
import com.momens.android.data.project.taskdetail.remote.dto.request.UpdateChecklistItemRequest
import com.momens.android.data.project.taskdetail.remote.dto.response.TaskDetailResponse
import com.momens.android.data.project.taskdetail.remote.dto.response.UpdateChecklistItemResponse
import com.momens.android.data.project.taskdetail.remote.service.TaskDetailService
import javax.inject.Inject

class TaskDetailRemoteDataSourceImpl @Inject constructor(
    private val taskDetailService: TaskDetailService,
) : TaskDetailRemoteDataSource {

    override suspend fun getTaskDetail(
        taskId: String,
    ): TaskDetailResponse {
        return taskDetailService.getTaskDetail(taskId = taskId)
    }

    override suspend fun updateChecklistItem(
        taskId: String,
        itemId: String,
        completed: Boolean,
    ): UpdateChecklistItemResponse {
        return taskDetailService.updateChecklistItem(
            taskId = taskId,
            itemId = itemId,
            request = UpdateChecklistItemRequest(completed = completed),
        )
    }
}
