package com.momens.android.data.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefProjectResponse

data class BriefProjectModel(
    val id: String,
    val name: String,
    val targetDate: String?,
    val progress: Int,
    val summary: String?,
)

fun BriefProjectResponse.toModel(): BriefProjectModel = BriefProjectModel(
    id = id,
    name = name,
    targetDate = targetDate,
    progress = progress,
    summary = summary,
)
