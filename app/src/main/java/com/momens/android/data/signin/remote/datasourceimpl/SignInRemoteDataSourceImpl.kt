package com.momens.android.data.signin.remote.datasourceimpl

import com.momens.android.data.signin.remote.datasource.SignInRemoteDataSource
import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.response.MobileBootstrapResponse
import com.momens.android.data.signin.remote.dto.response.SignInTokenResponse
import com.momens.android.data.signin.remote.service.SignInService
import javax.inject.Inject

class SignInRemoteDataSourceImpl @Inject constructor(
    private val signInService: SignInService,
) : SignInRemoteDataSource {

    override suspend fun postGoogleToken(
        request: SignInTokenRequest,
    ): SignInTokenResponse {
        return signInService.postGoogleToken(request)
    }

    override suspend fun getMobileBootstrap(): MobileBootstrapResponse {
        return signInService.getMobileBootstrap()
    }
}
