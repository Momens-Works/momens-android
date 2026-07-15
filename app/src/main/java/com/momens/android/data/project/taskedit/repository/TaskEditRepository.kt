package com.momens.android.data.project.taskedit.repository

import com.momens.android.data.project.taskedit.model.Member
import com.momens.android.data.project.taskedit.remote.dto.request.TaskEditRequestDto
import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMemberlistResponseDto

interface TaskEditRepository {
    suspend fun patchTaskEdit(taskId: String, request: TaskEditRequestDto) : Result<Unit>

    suspend fun getTaskEditMembers(projectId: String, query: String?): Result<List<Member>>
}
