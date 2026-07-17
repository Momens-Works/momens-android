package com.momens.android.data.pushdevice.remote.dto.request

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class PushDeviceRegisterRequest(
    @SerialName("fcm_registration_token")
    val fcmRegistrationToken: String,

    @EncodeDefault
    @SerialName("platform")
    val platform: String = "android",
)
