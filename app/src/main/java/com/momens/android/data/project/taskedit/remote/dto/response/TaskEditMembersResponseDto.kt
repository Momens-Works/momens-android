package com.momens.android.data.project.taskedit.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskEditMembersResponseDto(
    @SerialName("members")
    val members: List<MembersDto>
)

@Serializable
data class MembersDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)
