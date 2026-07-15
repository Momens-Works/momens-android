package com.momens.android.data.project.taskedit.repository

import com.momens.android.data.project.taskedit.model.MemberModel
import com.momens.android.data.project.taskedit.model.TaskEditModel
import com.momens.android.data.project.taskedit.remote.dto.request.TaskEditRequestDto

interface TaskEditRepository {
    suspend fun patchTaskEdit(taskId: String, request: TaskEditModel) : Result<Unit>

    suspend fun getTaskEditMembers(projectId: String, query: String?): Result<List<MemberModel>>
}

