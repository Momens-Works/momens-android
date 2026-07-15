package com.momens.android.data.project.taskedit.repositoryimpl

import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.project.taskedit.mapper.toModel
import com.momens.android.data.project.taskedit.model.Member
import com.momens.android.data.project.taskedit.remote.datasource.TaskEditRemoteDataSource
import com.momens.android.data.project.taskedit.remote.dto.request.TaskEditRequestDto
import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMemberlistResponseDto
import com.momens.android.data.project.taskedit.repository.TaskEditRepository
import javax.inject.Inject

internal class TaskEditRepositoryImpl @Inject constructor(
    private val remoteDataSource: TaskEditRemoteDataSource,
) : TaskEditRepository {

    override suspend fun patchTaskEdit(taskId: String, request: TaskEditRequestDto) : Result<Unit> =
        suspendRunCatching {
            remoteDataSource.patchTaskEdit(taskId = taskId, request = request)
        }


    override suspend fun getTaskEditMembers(projectId: String, query: String?): Result<List<Member>> =
        suspendRunCatching {
            remoteDataSource.getTaskEditMembers(projectId, query).toModel()
        }
}
