package com.momens.android.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.momens.android.BuildConfig
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * TokenManager의 DataStore 구현체입니다.
 *
 * access token과 refresh token을 Preferences DataStore에 저장하고, 앱 프로세스 안에서는 메모리 캐시를 먼저
 * 확인해 반복적인 DataStore 읽기를 줄입니다.
 *
 * 예시 - Hilt로 주입받아 사용
 * ```
 * class AuthRepository @Inject constructor(
 *     private val tokenManager: TokenManager,
 * ) {
 *     suspend fun saveLoginTokens(response: GoogleTokenResponse) {
 *         tokenManager.saveTokens(
 *             accessToken = response.accessToken,
 *             refreshToken = response.refreshToken,
 *         )
 *     }
 * }
 * ```
 */
class TokenManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : TokenManager {

    @Volatile
    private var cachedAccessToken: String? = BuildConfig.DEBUG_ACCESS_TOKEN.takeIf { it.isNotBlank() }

    @Volatile
    private var cachedRefreshToken: String? = BuildConfig.DEBUG_REFRESH_TOKEN

    override suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        dataStore.edit { preferences ->
            preferences[KEY_ACCESS_TOKEN] = accessToken
            preferences[KEY_REFRESH_TOKEN] = refreshToken
        }

        cachedAccessToken = accessToken
        cachedRefreshToken = refreshToken
    }

    override suspend fun getAccessToken(): String? {
        return cachedAccessToken ?: dataStore.data.map { preferences ->
            preferences[KEY_ACCESS_TOKEN]
        }.first().also {
            cachedAccessToken = it
        }
    }

    override suspend fun getRefreshToken(): String? {
        return cachedRefreshToken ?: dataStore.data.map { preferences ->
            preferences[KEY_REFRESH_TOKEN]
        }.first().also {
            cachedRefreshToken = it
        }
    }

    override suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_ACCESS_TOKEN)
            preferences.remove(KEY_REFRESH_TOKEN)
        }

        cachedAccessToken = null
        cachedRefreshToken = null
    }

    companion object {
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}
