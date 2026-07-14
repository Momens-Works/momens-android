package com.momens.android.core.local

interface ProjectManager {

    suspend fun saveProjectId(projectId: String?)

    suspend fun getProjectId(): String?

    suspend fun clearProjectId()
}
