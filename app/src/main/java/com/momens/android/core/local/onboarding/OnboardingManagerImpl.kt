package com.momens.android.core.local.onboarding

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class OnboardingManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : OnboardingManager {

    override suspend fun getHasSeenOnboarding(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[KEY_HAS_SEEN_ONBOARDING] ?: false
        }.first()
    }

    override suspend fun saveHasSeenOnboarding(hasSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_HAS_SEEN_ONBOARDING] = hasSeen
        }
    }

    private companion object {
        private val KEY_HAS_SEEN_ONBOARDING = booleanPreferencesKey("has_seen_onboarding")
    }
}
