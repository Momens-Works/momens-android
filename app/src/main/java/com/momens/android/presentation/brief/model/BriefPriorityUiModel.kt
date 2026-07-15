package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.brief.remote.dto.response.BriefPriorityResponse

@Immutable
data class BriefPriorityUiModel(
    val rank: Int,
    val title: String,
    val taskId: String,
)

fun BriefPriorityResponse.toUiModel(): BriefPriorityUiModel = BriefPriorityUiModel(
    rank = rank,
    title = title,
    taskId = taskId,
)
