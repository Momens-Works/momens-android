package com.momens.android.presentation.brief

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.common.state.UiState
import com.momens.android.core.designsystem.component.header.MomensDefaultHeader
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.brief.component.BriefCurrentPriority
import com.momens.android.presentation.brief.component.BriefSignalFilterSummary
import com.momens.android.presentation.brief.component.BriefSignalSummary
import com.momens.android.presentation.brief.component.BriefSummaryCard

@Composable
fun BriefRoute(
    paddingValues: PaddingValues,
    viewModel: BriefViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState.value) {
        UiState.Empty,
        UiState.Loading,
            -> BriefLoadingScreen(
            paddingValues = paddingValues,
        )

        UiState.Failure -> BriefFailureScreen(
            paddingValues = paddingValues,
        )

        is UiState.Success -> BriefScreen(
            paddingValues = paddingValues,
            uiState = state.data,
            onProfileClick = {},
            onFilterClick = viewModel::selectSignalFilter,
            onSummaryMoreClick = viewModel::loadMoreSignalSummary,
            onSummaryFoldClick = viewModel::foldSignalSummary,
        )
    }
}

@Composable
private fun BriefScreen(
    paddingValues: PaddingValues,
    uiState: BriefUiState,
    onProfileClick: () -> Unit,
    onFilterClick: (String) -> Unit,
    onSummaryMoreClick: () -> Unit,
    onSummaryFoldClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        MomensDefaultHeader(
            onProfileClick = onProfileClick,
            backgroundColor = MomensTheme.colors.uiBg,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "오늘의 브리프",
                color = MomensTheme.colors.black,
                style = MomensTheme.typography.titleB20,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(10.dp))

            BriefSummaryCard(
                title = uiState.project.name,
                targetDate = uiState.project.targetDate,
                progress = uiState.project.progress,
                summary = uiState.project.summary,
            )

            Spacer(modifier = Modifier.height(24.dp))

            uiState.signalSummary.summary?.let { summary ->
                BriefSignalSummary(
                    count = uiState.signalSummary.totalCount,
                    content = summary,
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            BriefSignalFilterSummary(
                selectedFilterKey = uiState.signalSummary.selectedFilterKey,
                filters = uiState.signalSummary.filters,
                summaries = uiState.signalSummary.items,
                hasMoreSummaries = uiState.signalSummary.hasMore,
                isSummaryExpanded = uiState.signalSummary.isExpanded,
                onFilterClick = onFilterClick,
                onSummaryMoreClick = onSummaryMoreClick,
                onSummaryFoldClick = onSummaryFoldClick,
            )

            Spacer(modifier = Modifier.height(24.dp))

            BriefCurrentPriority(
                priorities = uiState.priorities,
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun BriefLoadingScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        Text(
            text = "브리프를 불러오는 중입니다.",
            modifier = Modifier.padding(20.dp),
            color = MomensTheme.colors.gray700,
            style = MomensTheme.typography.bodyM14,
        )
    }
}

@Composable
private fun BriefFailureScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        Text(
            text = "브리프를 불러오지 못했습니다.",
            modifier = Modifier.padding(20.dp),
            color = MomensTheme.colors.gray700,
            style = MomensTheme.typography.bodyM14,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEFF1F1)
@Composable
private fun BriefScreenPreview() {
    MomensTheme {
        BriefScreen(
            paddingValues = PaddingValues(),
            uiState = SampleBriefUiState,
            onProfileClick = {},
            onFilterClick = {},
            onSummaryMoreClick = {},
            onSummaryFoldClick = {},
        )
    }
}
