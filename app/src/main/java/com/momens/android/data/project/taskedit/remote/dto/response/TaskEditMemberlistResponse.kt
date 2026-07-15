package com.momens.android.data.project.taskedit.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskEditMemberlistResponse(
    @SerialName("members")
    val members: List<Members>
)

@Serializable
data class Members(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)
