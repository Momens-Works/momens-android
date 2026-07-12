package com.momens.android.core.network

import com.momens.android.core.local.TokenManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.HttpException
import timber.log.Timber

/**
 * 서버가 401을 반환했을 때 호출되는 OkHttp Authenticator입니다.
 *
 * refresh token으로 새 토큰을 발급받고, 원래 요청에 새 Authorization 헤더를 붙여 한 번 재시도합니다.
 */
@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val tokenRefreshService: TokenRefreshService,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        Timber.d("인증 정보 없음 (401). 토큰 갱신 필요")

        if (response.retryCount() >= MAX_RETRY_COUNT) {
            Timber.d("토큰 갱신 재시도 횟수 초과")
            runBlocking { tokenManager.clearTokens() }
            return null
        }

        return runBlocking {
            val refreshToken = tokenManager.getRefreshToken() ?: return@runBlocking null.also {
                Timber.d("저장된 refresh token 없음")
                tokenManager.clearTokens()
            }

            runCatching {
                val tokenResponse = tokenRefreshService.refreshToken(
                    request = TokenRefreshRequest(refreshToken = refreshToken)
                )

                tokenManager.saveTokens(
                    accessToken = tokenResponse.accessToken,
                    refreshToken = tokenResponse.refreshToken,
                )

                response.request.newBuilder()
                    .header(AUTHORIZATION_HEADER, "$BEARER_PREFIX ${tokenResponse.accessToken}")
                    .build()
            }.onFailure { throwable ->
                when (throwable) {
                    is HttpException -> Timber.d(throwable, "토큰 갱신 실패: ${throwable.code()}")
                    else -> Timber.d(throwable, "토큰 갱신 실패")
                }
                tokenManager.clearTokens()
            }.getOrNull()
        }
    }

    private fun Response.retryCount(): Int {
        var currentResponse: Response? = this
        var count = 1

        while (currentResponse?.priorResponse != null) {
            count++
            currentResponse = currentResponse.priorResponse
        }

        return count
    }

    companion object {
        private const val MAX_RETRY_COUNT = 2
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER_PREFIX = "Bearer"
    }
}
