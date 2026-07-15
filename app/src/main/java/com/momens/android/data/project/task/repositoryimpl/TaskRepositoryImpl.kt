package com.momens.android.data.project.task.repositoryimpl

import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.project.task.model.TaskBoard
import com.momens.android.data.project.task.model.TaskCreateModel
import com.momens.android.data.project.task.model.TaskItem
import com.momens.android.data.project.task.model.toDto
import com.momens.android.data.project.task.model.toModel
import com.momens.android.data.project.task.remote.datasource.TaskRemoteDataSource
import com.momens.android.data.project.task.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskRemoteDataSource: TaskRemoteDataSource,
) : TaskRepository {
    override suspend fun getTaskBoard(projectId: String): Result<TaskBoard> = suspendRunCatching {
        taskRemoteDataSource.getTaskBoard(projectId).toModel()
    }

    override suspend fun createTask(
        projectId: String,
        task: TaskCreateModel,
    ): Result<TaskItem> = suspendRunCatching {
        taskRemoteDataSource.createTask(
            projectId = projectId,
            request = task.toDto(),
        ).toModel()
    }
}
