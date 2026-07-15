package com.momens.android.data.project.taskedit.model

import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMemberlistResponse

data class MemberModel(
    val id: String,
    val name: String,
    val avatarUrl: String?,
)

fun TaskEditMemberlistResponse.toModel(): List<MemberModel> =
    members.map { MemberModel(id = it.id, name = it.name, avatarUrl = it.avatarUrl) }
