package com.momens.android.data.signin.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleTokenRequest(
    @SerialName("id_token")
    val idToken: String,

    @SerialName("device")
    val device: String,
)
