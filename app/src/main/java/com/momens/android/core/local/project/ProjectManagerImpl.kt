package com.momens.android.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.momens.android.core.local.model.ProjectContextModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ProjectManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : ProjectManager {

    @Volatile
    override var currentProjectContext: ProjectContextModel = ProjectContextModel()
        private set

    override suspend fun saveProjectContext(projectContext: ProjectContextModel) {
        dataStore.edit { preferences ->
            if (projectContext.projectId == null) {
                preferences.remove(KEY_PROJECT_ID)
            } else {
                preferences[KEY_PROJECT_ID] = projectContext.projectId
            }

            if (projectContext.avatarUrl == null) {
                preferences.remove(KEY_AVATAR_URL)
            } else {
                preferences[KEY_AVATAR_URL] = projectContext.avatarUrl
            }
        }

        currentProjectContext = projectContext
    }

    override fun observeProjectContext(): Flow<ProjectContextModel> {
        return dataStore.data.map { preferences ->
            ProjectContextModel(
                projectId = preferences[KEY_PROJECT_ID],
                avatarUrl = preferences[KEY_AVATAR_URL],
            )
        }.onEach { projectContext ->
            currentProjectContext = projectContext
        }
    }

    override suspend fun clearProjectContext() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_PROJECT_ID)
            preferences.remove(KEY_AVATAR_URL)
        }

        currentProjectContext = ProjectContextModel()
    }

    companion object {
        private val KEY_PROJECT_ID = stringPreferencesKey("Project_Id")
        private val KEY_AVATAR_URL = stringPreferencesKey("avatar_url")
    }
}
