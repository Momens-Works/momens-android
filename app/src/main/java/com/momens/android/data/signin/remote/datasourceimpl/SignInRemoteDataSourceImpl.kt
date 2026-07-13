package com.momens.android.data.signin.remote.datasourceimpl

import com.momens.android.data.signin.remote.datasource.SignInRemoteDataSource
import com.momens.android.data.signin.remote.dto.request.GoogleTokenRequest
import com.momens.android.data.signin.remote.dto.response.GoogleTokenResponse
import com.momens.android.data.signin.remote.service.SignInService
import javax.inject.Inject

class SignInRemoteDataSourceImpl @Inject constructor(
    private val signInService: SignInService,
) : SignInRemoteDataSource {

    override suspend fun postGoogleToken(
        request: GoogleTokenRequest,
    ): GoogleTokenResponse {
        return signInService.postGoogleToken(request)
    }
}
