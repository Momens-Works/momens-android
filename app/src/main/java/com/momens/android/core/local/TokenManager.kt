package com.momens.android.core.local

/**
 * 로그인/토큰 갱신으로 받은 인증 토큰을 저장하고 조회하는 인터페이스입니다.
 *
 * 예시 - 로그인 성공 후 토큰 저장
 * ```
 * val response = authService.signInWithGoogle(request)
 * tokenManager.saveTokens(
 *     accessToken = response.accessToken,
 *     refreshToken = response.refreshToken,
 * )
 * ```
 *
 * 예시 - 로그아웃 또는 인증 만료 시 토큰 삭제
 * ```
 * tokenManager.clearTokens()
 * ```
 */
interface TokenManager {

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    )

    suspend fun getAccessToken(): String?

    suspend fun getRefreshToken(): String?

    suspend fun clearTokens()
}
