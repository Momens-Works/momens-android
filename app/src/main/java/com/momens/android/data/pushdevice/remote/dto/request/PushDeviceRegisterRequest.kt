package com.momens.android.data.pushdevice.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PushDeviceRegisterRequest(
    @SerialName("fcm_registration_token")
    val fcmRegistrationToken: String,

    @SerialName("platform")
    val platform: String = "android",
)
