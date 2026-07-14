package com.momens.android.data.project.taskdetail.repositoryimpl

import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.project.taskdetail.model.TaskDetailModel
import com.momens.android.data.project.taskdetail.model.UpdateChecklistItemResult
import com.momens.android.data.project.taskdetail.model.toModel
import com.momens.android.data.project.taskdetail.remote.datasource.TaskDetailRemoteDataSource
import com.momens.android.data.project.taskdetail.repository.TaskDetailRepository
import javax.inject.Inject

class TaskDetailRepositoryImpl @Inject constructor(
    private val taskDetailRemoteDataSource: TaskDetailRemoteDataSource,
) : TaskDetailRepository {

    override suspend fun getTaskDetail(
        taskId: String,
    ): Result<TaskDetailModel> = suspendRunCatching {
        taskDetailRemoteDataSource.getTaskDetail(taskId = taskId).toModel()
    }

    override suspend fun updateChecklistItem(
        taskId: String,
        itemId: String,
        completed: Boolean,
    ): Result<UpdateChecklistItemResult> = suspendRunCatching {
        taskDetailRemoteDataSource.updateChecklistItem(
            taskId = taskId,
            itemId = itemId,
            completed = completed,
        ).toModel()
    }
}
