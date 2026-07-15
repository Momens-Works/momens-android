package com.momens.android.data.signin.remote.datasourceimpl

import com.momens.android.data.signin.remote.datasource.SignInRemoteDataSource
import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.request.TokenRefreshRequest
import com.momens.android.data.signin.remote.dto.response.TokenResponse
import com.momens.android.data.signin.remote.service.SignInService
import javax.inject.Inject

class SignInRemoteDataSourceImpl @Inject constructor(
    private val signInService: SignInService,
) : SignInRemoteDataSource {

    override suspend fun postGoogleToken(
        request: SignInTokenRequest,
    ): TokenResponse {
        return signInService.postGoogleToken(request)
    }

    override suspend fun refreshToken(
        request: TokenRefreshRequest,
    ): TokenResponse {
        return signInService.refreshToken(request)
    }
}
