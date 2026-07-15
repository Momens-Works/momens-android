package com.momens.android.presentation.brief.state

import androidx.compose.runtime.Immutable
import com.momens.android.data.brief.remote.dto.response.BriefPriorityResponse
import com.momens.android.data.brief.remote.dto.response.BriefResponse
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterType
import com.momens.android.presentation.brief.model.BriefPriorityUiModel
import com.momens.android.presentation.brief.model.BriefProjectUiModel
import com.momens.android.presentation.brief.model.BriefSignalSummaryUiModel
import com.momens.android.presentation.brief.model.toUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class BriefUiState(
    val project: BriefProjectUiModel,
    val signalSummary: BriefSignalSummaryUiModel,
    val priorities: ImmutableList<BriefPriorityUiModel>,
)

fun BriefResponse.toUiState(
    selectedFilterType: BriefSignalSummaryFilterType = BriefSignalSummaryFilterType.ALL,
): BriefUiState = BriefUiState(
    project = project.toUiModel(),
    signalSummary = signalSummary.toUiModel(selectedFilterType = selectedFilterType),
    priorities = priorities.map(BriefPriorityResponse::toUiModel).toImmutableList(),
)
