package com.momens.android.data.signin.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenRefreshResponse(
    @SerialName("access_token")
    val accessToken: String,

    @SerialName("refresh_token")
    val refreshToken: String,

    @SerialName("token_type")
    val tokenType: String,

    @SerialName("expires_in")
    val expiresIn: Long,
)
