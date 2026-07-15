package com.momens.android.presentation.project.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.project.taskedit.model.MemberModel
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Assignee(
    val id: String,
    val name: String,
    val url: String?,
)

fun MemberModel.toAssignee(): Assignee = Assignee(id = id, name = name, url = avatarUrl)
