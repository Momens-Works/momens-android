package com.momens.android.data.brief.model

import com.momens.android.data.brief.remote.dto.response.BriefResponse

data class BriefModel(
    val project: BriefProjectModel,
    val signalSummary: BriefSignalSummaryModel,
    val priorities: List<BriefPriorityModel>,
)

fun BriefResponse.toModel(): BriefModel = BriefModel(
    project = project.toModel(),
    signalSummary = signalSummary.toModel(),
    priorities = priorities.map { it.toModel() },
)
