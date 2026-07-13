package com.momens.android.core.network

import com.momens.android.core.local.TokenManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

/**
 * 모든 네트워크 요청에 공통 헤더를 추가하는 OkHttp Interceptor입니다.
 *
 * 기본으로 `API-Version: 1`, `Content-Type: application/json`을 붙이고, 인증이 필요한 API에는
 * `Authorization: Bearer {accessToken}`을 붙입니다.
 *
 * 예시 - 인증이 필요한 요청
 * ```
 * GET /api/mobile/projects
 *
 * API-Version: 1
 * Content-Type: application/json
 * Authorization: Bearer eyJ...
 * ```
 *
 * 예시 - Authorization이 필요 없는 요청
 * ```
 * POST /api/auth/google/token
 *
 * API-Version: 1
 * Content-Type: application/json
 * ```
 */
@Singleton
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val accessToken = runBlocking { tokenManager.getAccessToken() }
        val requestBuilder = originalRequest.newBuilder()
            .header(API_VERSION_HEADER, API_VERSION)
            .header(CONTENT_TYPE_HEADER, CONTENT_TYPE)

        if (accessToken != null && originalRequest.requiresAuthorization()) {
            requestBuilder.header(AUTHORIZATION_HEADER, "$BEARER_PREFIX $accessToken")
            Timber.d("인증 헤더 추가")
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun Request.requiresAuthorization(): Boolean {
        val path = url.encodedPath

        return path !in AUTHORIZATION_EXCLUDED_PATHS
    }

    companion object {
        private const val API_VERSION_HEADER = "API-Version"
        private const val API_VERSION = "1"
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER_PREFIX = "Bearer"
        private const val CONTENT_TYPE_HEADER = "Content-Type"
        private const val CONTENT_TYPE = "application/json"
        private val AUTHORIZATION_EXCLUDED_PATHS = setOf(
            "/api/auth/google/token",
            "/api/auth/refresh",
        )
    }
}
