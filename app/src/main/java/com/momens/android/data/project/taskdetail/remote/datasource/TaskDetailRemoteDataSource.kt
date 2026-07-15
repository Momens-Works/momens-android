package com.momens.android.data.project.taskdetail.remote.datasource

import com.momens.android.data.project.taskdetail.remote.dto.response.TaskDetailResponse
import com.momens.android.data.project.taskdetail.remote.dto.response.UpdateChecklistItemResponse

interface TaskDetailRemoteDataSource {

    suspend fun getTaskDetail(taskId: String): TaskDetailResponse

    suspend fun updateChecklistItem(
        taskId: String,
        itemId: String,
        completed: Boolean,
    ): UpdateChecklistItemResponse
}
