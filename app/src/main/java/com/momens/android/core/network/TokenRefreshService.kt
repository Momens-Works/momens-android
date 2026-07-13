package com.momens.android.core.network

import retrofit2.http.Body
import retrofit2.http.POST

interface TokenRefreshService {

    @POST("/api/auth/refresh")
    suspend fun refreshToken(
        @Body request: TokenRefreshRequest
    ): TokenRefreshResponse
}
