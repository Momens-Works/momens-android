package com.momens.android.data.project.taskdetail.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskMaterialDto(
    @SerialName("id")
    val id: String,

    @SerialName("title")
    val title: String,

    @SerialName("summary")
    val summary: String,

    @SerialName("roles")
    val roles: List<String>,

    @SerialName("kind")
    val kind: String,

    @SerialName("source_url")
    val sourceUrl: String,
)
