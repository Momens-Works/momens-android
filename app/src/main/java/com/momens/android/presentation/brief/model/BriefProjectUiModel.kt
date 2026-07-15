package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.brief.remote.dto.response.BriefProjectResponse

@Immutable
data class BriefProjectUiModel(
    val id: String,
    val name: String,
    val targetDate: String,
    val progress: Float,
    val summary: String,
)

fun BriefProjectResponse.toUiModel(): BriefProjectUiModel = BriefProjectUiModel(
    id = id,
    name = name,
    targetDate = targetDate.orEmpty(),
    progress = progress.toProgressFraction(),
    summary = summary.orEmpty(),
)

private fun Int.toProgressFraction(): Float {
    return if (this > 1) {
        this / 100f
    } else {
        toFloat()
    }.coerceIn(0f, 1f)
}
