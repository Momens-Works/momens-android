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

    @Volatile
    private var cachedAvatarUrl: String? = null

    override suspend fun saveProjectContext(
        projectId: String?,
        avatarUrl: String?,
    ) {
        dataStore.edit { preferences ->
            if (projectId == null) {
                preferences.remove(KEY_PROJECT_ID)
            } else {
                preferences[KEY_PROJECT_ID] = projectId
            }

            if (avatarUrl == null) {
                preferences.remove(KEY_AVATAR_URL)
            } else {
                preferences[KEY_AVATAR_URL] = avatarUrl
            }
        }

        cachedProjectId = projectId
        cachedAvatarUrl = avatarUrl
    }

    override suspend fun getProjectId(): String? {
        return cachedProjectId ?: dataStore.data.map { preferences ->
            preferences[KEY_PROJECT_ID]
        }.first().also {
            cachedProjectId = it
        }
    }

    override suspend fun getAvatarUrl(): String? {
        return cachedAvatarUrl ?: dataStore.data.map { preferences ->
            preferences[KEY_AVATAR_URL]
        }.first().also {
            cachedAvatarUrl = it
        }
    }

    override suspend fun clearProjectContext() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_PROJECT_ID)
            preferences.remove(KEY_AVATAR_URL)
        }

        cachedProjectId = null
        cachedAvatarUrl = null
    }

    companion object {
        private val KEY_PROJECT_ID = stringPreferencesKey("Project_Id")
        private val KEY_AVATAR_URL = stringPreferencesKey("avatar_url")
    }
}
