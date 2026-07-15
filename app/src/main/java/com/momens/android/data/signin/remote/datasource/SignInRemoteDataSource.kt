package com.momens.android.data.signin.remote.datasource

import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.request.TokenRefreshRequest
import com.momens.android.data.signin.remote.dto.response.TokenResponse

interface SignInRemoteDataSource {
    suspend fun postGoogleToken(
        request: SignInTokenRequest,
    ): TokenResponse

    suspend fun refreshToken(
        request: TokenRefreshRequest,
    ): TokenResponse
}
