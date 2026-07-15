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
    val kind: TaskMaterialKindDto,

    @SerialName("source_url")
    val sourceUrl: String,

    @SerialName("created_at")
    val createdAt: String? = null,
)

@Serializable
enum class TaskMaterialKindDto {
    @SerialName("SOURCE_TYPE_SLACK")
    SLACK,

    @SerialName("SOURCE_TYPE_GITHUB")
    GITHUB,

    @SerialName("SOURCE_TYPE_FIGMA")
    FIGMA,

    @SerialName("SOURCE_TYPE_FILE")
    FILE,
}
