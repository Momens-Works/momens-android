package com.momens.android.data.signin.remote.datasource

import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.request.TokenRefreshRequest
import com.momens.android.data.signin.remote.dto.response.SignInTokenResponse
import com.momens.android.data.signin.remote.dto.response.TokenRefreshResponse

interface SignInRemoteDataSource {
    suspend fun postGoogleToken(
        request: SignInTokenRequest,
    ): SignInTokenResponse

    suspend fun refreshToken(
        request: TokenRefreshRequest,
    ): TokenRefreshResponse
}
