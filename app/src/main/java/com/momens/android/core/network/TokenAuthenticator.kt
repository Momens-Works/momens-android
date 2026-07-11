package com.momens.android.core.network

import com.momens.android.core.local.TokenManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber

/**
 * 서버가 401을 반환했을 때 호출되는 OkHttp Authenticator입니다.
 *
 * 현재 구현은 저장된 토큰을 삭제하고 요청을 재시도하지 않습니다. 토큰 갱신 API가 연결되면 이 위치에서
 * refresh token으로 새 토큰을 발급받고, 원래 요청에 새 Authorization 헤더를 붙여 재시도하도록 확장합니다.
 *
 * 예시 - 추후 토큰 갱신 흐름
 * ```
 * val refreshToken = tokenManager.getRefreshToken()
 * val newTokens = authService.refreshToken(refreshToken)
 * tokenManager.saveTokens(newTokens.accessToken, newTokens.refreshToken)
 *
 * return response.request.newBuilder()
 *     .header("Authorization", "Bearer ${newTokens.accessToken}")
 *     .build()
 * ```
 */
@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        Timber.d("인증 정보 없음 (401). 토큰 갱신 필요")

        runBlocking {
            tokenManager.clearTokens()
        }

        return null
    }
}
