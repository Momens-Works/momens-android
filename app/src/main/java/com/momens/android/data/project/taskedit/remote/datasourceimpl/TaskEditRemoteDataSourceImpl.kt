package com.momens.android.data.project.taskedit.remote.datasourceimpl

import com.momens.android.data.project.taskedit.remote.datasource.TaskEditRemoteDataSource
import com.momens.android.data.project.taskedit.remote.dto.request.TaskEditRequestDto
import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMembersResponseDto
import com.momens.android.data.project.taskedit.remote.service.TaskEditService
import retrofit2.HttpException
import javax.inject.Inject

internal class TaskEditRemoteDataSourceImpl @Inject constructor(
    private val service: TaskEditService,
) : TaskEditRemoteDataSource {

    override suspend fun patchTaskEdit(taskId: String, request: TaskEditRequestDto) {
        val response = service.patchTaskEdit(taskId = taskId, request = request)

        if (!response.isSuccessful) {
            throw HttpException(response)
        }
    }

    override suspend fun getTaskEditMembers(projectId: String, query: String?): TaskEditMembersResponseDto {
        return service.getTaskEditMembers(projectId = projectId, query = query)
    }
}
