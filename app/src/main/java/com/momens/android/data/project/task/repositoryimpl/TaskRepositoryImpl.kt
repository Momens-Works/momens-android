package com.momens.android.data.project.task.repositoryimpl

import com.momens.android.data.project.task.model.TaskBoard
import com.momens.android.data.project.task.model.TaskGroup
import com.momens.android.data.project.task.model.TaskItem
import com.momens.android.data.project.task.remote.datasource.TaskRemoteDataSource
import com.momens.android.data.project.task.remote.dto.response.TaskBoardResponse
import com.momens.android.data.project.task.remote.dto.response.TaskGroupResponse
import com.momens.android.data.project.task.remote.dto.response.TaskResponse
import com.momens.android.data.project.task.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskRemoteDataSource: TaskRemoteDataSource,
) : TaskRepository {
    override suspend fun getTaskBoard(projectId: String): Result<TaskBoard> =
        runCatching { taskRemoteDataSource.getTaskBoard(projectId).toModel() }
}

fun TaskBoardResponse.toModel() = TaskBoard(
    title = title,
    description = description,
    groups = groups.map { it.toModel() },
)
fun TaskGroupResponse.toModel() = TaskGroup(groupKey, label, count, tasks.map { it.toModel() })
fun TaskResponse.toModel() = TaskItem(id, title, role, priority, materialCount)
