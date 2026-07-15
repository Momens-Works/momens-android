package com.momens.android.data.project.taskdetail.repository

import com.momens.android.data.project.taskdetail.model.TaskDetailModel
import com.momens.android.data.project.taskdetail.model.UpdateChecklistItemResult
import kotlinx.coroutines.flow.StateFlow

interface TaskDetailRepository {

    val cachedTaskDetail: StateFlow<TaskDetailModel?>

    suspend fun getTaskDetail(taskId: String): Result<TaskDetailModel>

    suspend fun updateChecklistItem(
        taskId: String,
        itemId: String,
        completed: Boolean,
    ): Result<UpdateChecklistItemResult>
}
