package com.momens.android.data.project.taskdetail.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskMaterialDto(
    @SerialName("id")
    val id: String,

    @SerialName("title")
    val title: String? = null,

    @SerialName("summary")
    val summary: String? = null,

    @SerialName("source")
    val kind: TaskMaterialKindDto,

    @SerialName("source_url")
    val sourceUrl: String? = null,

    @SerialName("occurred_at")
    val createdAt: String? = null,
)

@Serializable
enum class TaskMaterialKindDto {
    @SerialName("slack")
    SLACK,

    @SerialName("github")
    GITHUB,

    @SerialName("figma")
    FIGMA,

    @SerialName("file")
    FILE,
}
