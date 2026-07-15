package com.momens.android.data.project.taskedit.mapper

import com.momens.android.data.project.taskedit.model.Member
import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMemberlistResponseDto

fun TaskEditMemberlistResponseDto.toModel(): List<Member> =
    members.map { Member(id = it.id, name = it.name, avatarUrl = it.avatarUrl) }
