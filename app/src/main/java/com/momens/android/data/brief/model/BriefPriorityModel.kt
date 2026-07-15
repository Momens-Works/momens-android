package com.momens.android.data.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefPriorityResponse

data class BriefPriorityModel(
    val rank: Int,
    val title: String,
    val taskId: String,
)

fun BriefPriorityResponse.toModel(): BriefPriorityModel = BriefPriorityModel(
    rank = rank,
    title = title,
    taskId = taskId,
)
