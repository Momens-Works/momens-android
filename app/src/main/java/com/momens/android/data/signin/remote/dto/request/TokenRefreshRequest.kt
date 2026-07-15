package com.momens.android.data.signin.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenRefreshRequest(
    @SerialName("refresh_token")
    val refreshToken: String,
)
