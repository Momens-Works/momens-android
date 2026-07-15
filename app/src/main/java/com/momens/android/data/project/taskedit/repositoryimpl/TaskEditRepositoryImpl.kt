package com.momens.android.data.project.taskedit.repositoryimpl

import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.project.taskedit.model.MemberModel
import com.momens.android.data.project.taskedit.model.TaskEditModel
import com.momens.android.data.project.taskedit.model.toModel
import com.momens.android.data.project.taskedit.model.toRequest
import com.momens.android.data.project.taskedit.remote.datasource.TaskEditRemoteDataSource
import com.momens.android.data.project.taskedit.repository.TaskEditRepository
import javax.inject.Inject

internal class TaskEditRepositoryImpl @Inject constructor(
    private val remoteDataSource: TaskEditRemoteDataSource,
) : TaskEditRepository {

    override suspend fun patchTaskEdit(taskId: String, request: TaskEditModel): Result<Unit> =
        suspendRunCatching {
            remoteDataSource.patchTaskEdit(
                taskId = taskId,
                request = request.toRequest(),
            )
        }


    override suspend fun getTaskEditMembers(projectId: String, query: String?): Result<List<MemberModel>> =
        suspendRunCatching {
            remoteDataSource.getTaskEditMembers(projectId, query).toModel()
        }
}
