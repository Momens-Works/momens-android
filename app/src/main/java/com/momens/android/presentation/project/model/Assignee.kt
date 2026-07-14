package com.momens.android.presentation.project.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Assignee(
    val id: String,
    val name: String,
    val url: String?,
)
