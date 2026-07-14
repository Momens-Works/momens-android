package com.momens.android.data.project.taskdetail.repository

import com.momens.android.data.project.taskdetail.model.TaskDetailModel
import com.momens.android.data.project.taskdetail.model.UpdateChecklistItemResult

interface TaskDetailRepository {

    suspend fun getTaskDetail(taskId: String): Result<TaskDetailModel>

    suspend fun updateChecklistItem(
        taskId: String,
        itemId: String,
        completed: Boolean,
    ): Result<UpdateChecklistItemResult>
}
