package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.brief.model.BriefProjectModel

@Immutable
data class BriefProjectUiModel(
    val id: String,
    val name: String,
    val targetDate: String,
    val progress: Float,
    val summary: String,
)

fun BriefProjectModel.toUiModel(): BriefProjectUiModel = BriefProjectUiModel(
    id = id,
    name = name,
    targetDate = targetDate.orEmpty(),
    progress = progress.toProgressFraction(),
    summary = summary.orEmpty(),
)
