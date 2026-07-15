package com.momens.android.data.signin.remote.service

import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.request.TokenRefreshRequest
import com.momens.android.data.signin.remote.dto.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST("/api/auth/google/token")
    suspend fun postGoogleToken(
        @Body request: SignInTokenRequest,
    ): TokenResponse

    @POST("/api/auth/refresh")
    suspend fun getNewAccessToken(
        @Body request: TokenRefreshRequest,
    ): TokenResponse
}
