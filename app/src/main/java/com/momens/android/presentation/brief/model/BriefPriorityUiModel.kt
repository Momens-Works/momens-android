package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.brief.model.BriefPriorityModel

@Immutable
data class BriefPriorityUiModel(
    val rank: Int,
    val title: String,
    val taskId: String,
)

fun BriefPriorityModel.toUiModel(): BriefPriorityUiModel = BriefPriorityUiModel(
    rank = rank,
    title = title,
    taskId = taskId,
)
