package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryItemResponse

@Immutable
data class BriefSignalItemUiModel(
    val id: String,
    val type: BriefSignalSummaryFilterType,
    val title: String,
)

fun BriefSignalSummaryItemResponse.toUiModel(): BriefSignalItemUiModel = BriefSignalItemUiModel(
    id = id,
    type = type.toFilterType(),
    title = title,
)
