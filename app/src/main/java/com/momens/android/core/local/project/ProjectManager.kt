package com.momens.android.core.local.project

import com.momens.android.core.local.model.ProjectContextModel
import kotlinx.coroutines.flow.Flow

interface ProjectManager {

    val currentProjectContext: ProjectContextModel

    suspend fun saveProjectContext(projectContext: ProjectContextModel)

    fun observeProjectContext(): Flow<ProjectContextModel>

    suspend fun clearProjectContext()
}
