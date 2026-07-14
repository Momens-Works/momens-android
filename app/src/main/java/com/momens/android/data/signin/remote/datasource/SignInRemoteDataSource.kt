package com.momens.android.data.signin.remote.datasource

import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.response.SignInTokenResponse

interface SignInRemoteDataSource {
    suspend fun postGoogleToken(
        request: SignInTokenRequest,
    ): SignInTokenResponse
}
