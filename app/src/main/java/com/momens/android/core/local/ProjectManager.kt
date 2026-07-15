package com.momens.android.core.local

interface ProjectManager {

    suspend fun saveProjectContext(
        projectId: String?,
        avatarUrl: String?,
    )

    suspend fun getProjectId(): String?

    suspend fun getAvatarUrl(): String?

    suspend fun clearProjectContext()
}
