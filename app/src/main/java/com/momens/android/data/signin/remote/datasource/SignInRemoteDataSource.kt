package com.momens.android.data.signin.remote.datasource

import com.momens.android.data.signin.remote.dto.request.GoogleTokenRequest
import com.momens.android.data.signin.remote.dto.response.GoogleTokenResponse

interface SignInRemoteDataSource {
    suspend fun postGoogleToken(
        request: GoogleTokenRequest,
    ): GoogleTokenResponse
}
