package com.momens.android.data.signin.remote.service

import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.request.TokenRefreshRequest
import com.momens.android.data.signin.remote.dto.response.SignInTokenResponse
import com.momens.android.data.signin.remote.dto.response.TokenRefreshResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST("/api/auth/google/token")
    suspend fun postGoogleToken(
        @Body request: SignInTokenRequest,
    ): SignInTokenResponse

    @POST("/api/auth/refresh")
    suspend fun refreshToken(
        @Body request: TokenRefreshRequest,
    ): TokenRefreshResponse
}
