package com.momens.android.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ProjectManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : ProjectManager {

    @Volatile
    private var cachedProjectId: String? = null

    override suspend fun saveProjectId(projectId: String?) {
        dataStore.edit { preferences ->
            if (projectId == null) {
                preferences.remove(KEY_PROJECT_ID)
            } else {
                preferences[KEY_PROJECT_ID] = projectId
            }
        }

        cachedProjectId = projectId
    }

    override suspend fun getProjectId(): String? {
        return cachedProjectId ?: dataStore.data.map { preferences ->
            preferences[KEY_PROJECT_ID]
        }.first().also {
            cachedProjectId = it
        }
    }

    override suspend fun clearProjectId() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_PROJECT_ID)
        }

        cachedProjectId = null
    }

    companion object {
        private val KEY_PROJECT_ID = stringPreferencesKey("Project_Id")
    }
}
