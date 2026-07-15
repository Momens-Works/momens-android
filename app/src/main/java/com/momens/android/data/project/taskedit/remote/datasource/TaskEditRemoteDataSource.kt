package com.momens.android.data.project.taskedit.remote.datasource

import com.momens.android.data.project.taskedit.remote.dto.request.TaskEditRequestDto
import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMemberlistResponseDto

internal interface TaskEditRemoteDataSource {
    suspend fun patchTaskEdit(taskId: String, request: TaskEditRequestDto)
    suspend fun getTaskEditMembers(projectId: String, query: String?): TaskEditMemberlistResponseDto
}
