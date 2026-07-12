package com.momens.android.data.brief.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BriefResponse(
    @SerialName("project")
    val project: BriefProjectResponse,
    @SerialName("signal_summary")
    val signalSummary: BriefSignalSummaryResponse,
    @SerialName("priorities")
    val priorities: List<BriefPriorityResponse>,
)

@Serializable
data class BriefProjectResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("target_date")
    val targetDate: String,
    @SerialName("progress")
    val progress: Int,
    @SerialName("summary")
    val summary: String,
)

@Serializable
data class BriefSignalSummaryResponse(
    @SerialName("summary")
    val summary: String?,
    @SerialName("filters")
    val filters: List<BriefSignalSummaryFilterResponse> = emptyList(),
    @SerialName("items")
    val items: List<BriefSignalSummaryItemResponse> = emptyList(),
    @SerialName("next_cursor")
    val nextCursor: String?,
)

@Serializable
data class BriefSignalSummaryFilterResponse(
    @SerialName("key")
    val key: String,
    @SerialName("label")
    val label: String,
    @SerialName("count")
    val count: Int,
)

@Serializable
data class BriefSignalSummaryItemResponse(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String,
)

@Serializable
data class BriefPriorityResponse(
    @SerialName("rank")
    val rank: Int,
    @SerialName("title")
    val title: String,
    @SerialName("task_id")
    val taskId: String,
)
