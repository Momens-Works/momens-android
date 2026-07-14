package com.momens.android.data.signin.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MobileBootstrapResponse(
    @SerialName("default_project_id")
    val defaultProjectId: String?,
)
