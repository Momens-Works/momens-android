package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.data.brief.model.BriefSignalSummaryItemModel

@Immutable
data class BriefSignalItemUiModel(
    val id: String,
    val type: BriefSignalSummaryFilterType,
    val title: String,
)

fun BriefSignalSummaryItemModel.toUiModel(): BriefSignalItemUiModel = BriefSignalItemUiModel(
    id = id,
    type = type.toFilterType(),
    title = title,
)
