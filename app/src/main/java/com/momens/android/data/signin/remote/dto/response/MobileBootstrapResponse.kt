package com.momens.android.data.signin.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MobileBootstrapResponse(
    @SerialName("me")
    val me: MobileBootstrapMeResponse,
    @SerialName("default_project_id")
    val defaultProjectId: String?,
    @SerialName("projects")
    val projects: List<MobileBootstrapProjectResponse>,
)

@Serializable
data class MobileBootstrapMeResponse(
    @SerialName("user")
    val user: MobileBootstrapUserResponse,
)

@Serializable
data class MobileBootstrapUserResponse(
    @SerialName("id")
    val id: String,
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("avatar_url")
    val avatarUrl: String?,
)

@Serializable
data class MobileBootstrapProjectResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("role")
    val role: String,
)
