package com.momens.android.data.signin.remote.service

import com.momens.android.data.signin.remote.dto.request.GoogleTokenRequest
import com.momens.android.data.signin.remote.dto.response.GoogleTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST("/api/auth/google/token")
    suspend fun postGoogleToken(
        @Body request: GoogleTokenRequest,
    ): GoogleTokenResponse
}
