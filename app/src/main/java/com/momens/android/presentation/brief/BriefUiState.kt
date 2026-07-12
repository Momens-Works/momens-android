package com.momens.android.presentation.brief

import androidx.compose.runtime.Immutable
import com.momens.android.presentation.brief.model.BriefPriorityUiModel
import com.momens.android.presentation.brief.model.BriefProjectUiModel
import com.momens.android.presentation.brief.model.BriefSignalSummaryUiModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class BriefUiState(
    val project: BriefProjectUiModel,
    val signalSummary: BriefSignalSummaryUiModel,
    val priorities: ImmutableList<BriefPriorityUiModel>,
)
