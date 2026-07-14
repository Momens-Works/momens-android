package com.momens.android.presentation.project.taskedit.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class AssigneeInfo(
    val id: String,
    val name: String,
    val url: String?,
)
