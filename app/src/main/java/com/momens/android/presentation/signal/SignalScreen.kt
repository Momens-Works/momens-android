package com.momens.android.presentation.signal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.common.extension.collectSideEffect
import com.momens.android.core.common.state.UiState
import com.momens.android.core.designsystem.component.emptyview.MomensEmptyView
import com.momens.android.core.designsystem.component.header.MomensDefaultHeader
import com.momens.android.core.designsystem.component.pagetitle.MomensPageTitle
import com.momens.android.core.designsystem.component.snackbar.model.MomensSnackbarModel
import com.momens.android.core.designsystem.component.type.MomensSnackbarType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.core.designsystem.trigger.LocalGlobalUiEventTrigger
import com.momens.android.core.designsystem.trigger.SnackbarState
import com.momens.android.presentation.signal.component.SignalList
import com.momens.android.presentation.signal.component.detail.SignalDetailBottomSheet
import com.momens.android.presentation.signal.model.SignalCardUiModel
import com.momens.android.presentation.signal.state.SignalSideEffect
import com.momens.android.presentation.signal.state.SignalState
import com.momens.android.presentation.signal.viewmodel.SignalViewModel
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SignalRoute(
    paddingValues: PaddingValues,
    navigateToTask: () -> Unit,
    pendingSignalId: String? = null,
    onPendingSignalConsumed: () -> Unit = {},
    viewModel: SignalViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val projectContext by viewModel.projectContext.collectAsStateWithLifecycle()
    val globalTrigger = LocalGlobalUiEventTrigger.current

    LaunchedEffect(Unit) {
        viewModel.loadSignals()
    }

    var hasRetriedLoadForPendingSignal by remember(pendingSignalId) { mutableStateOf(false) }

    LaunchedEffect(state, pendingSignalId) {
        val signalId = pendingSignalId ?: return@LaunchedEffect
        val currentData = (state as? UiState.Success)?.data ?: return@LaunchedEffect

        val alreadyLoaded = currentData.signals.any { it.id == signalId }
        if (alreadyLoaded || hasRetriedLoadForPendingSignal) return@LaunchedEffect

        hasRetriedLoadForPendingSignal = true
        viewModel.loadSignals()
    }

    viewModel.sideEffect.collectSideEffect {
        when (it) {
            is SignalSideEffect.ShowActionSnackbar -> {
                globalTrigger.showSnackbar(
                    SnackbarState(
                        content = MomensSnackbarModel(
                            title = it.message,
                            description = it.description,
                            type = MomensSnackbarType.BUTTON,
                            onActionClick = navigateToTask,
                        ),
                    ),
                )
            }

            is SignalSideEffect.ShowSnackbar -> {
                globalTrigger.showSnackbar(
                    SnackbarState(
                        content = MomensSnackbarModel(title = it.message),
                    ),
                )
            }
        }
    }

    when (val currentState = state) {
        UiState.Loading, UiState.Failure -> {
            // TODO: 로딩 화면 연결 예정
        }

        is UiState.Success -> {
            SignalScreen(
                state = currentState.data,
                avatarUrl = projectContext.avatarUrl,
                paddingValues = paddingValues,
                onSignalClick = viewModel::onSignalClick,
                onDeleteSignal = viewModel::deleteSignal,
                onRegisterTask = viewModel::registerTask,
                pendingSignalId = pendingSignalId,
                onPendingSignalConsumed = onPendingSignalConsumed,
            )
        }

        UiState.Empty -> Unit
    }
}

private const val EMPTY_STATE_TOP_WEIGHT = 80f
private const val EMPTY_STATE_BOTTOM_WEIGHT = 270f

@Composable
private fun SignalScreen(
    state: SignalState,
    avatarUrl: String?,
    paddingValues: PaddingValues,
    onSignalClick: (String) -> Unit,
    onDeleteSignal: (String) -> Unit,
    onRegisterTask: (String) -> Unit,
    modifier: Modifier = Modifier,
    pendingSignalId: String? = null,
    onPendingSignalConsumed: () -> Unit = {},
) {
    var selectedSignal by remember { mutableStateOf<SignalCardUiModel?>(null) }

    LaunchedEffect(state.signals, pendingSignalId) {
        if (pendingSignalId == null) return@LaunchedEffect

        val target = state.signals.find { it.id == pendingSignalId } ?: return@LaunchedEffect

        selectedSignal = target
        onSignalClick(target.id)
        onPendingSignalConsumed()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        MomensDefaultHeader(
            onProfileClick = {},
            avatarUrl = avatarUrl,
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomensPageTitle(
            title = state.pageTitle,
            subtitle = state.pageDescription,
            modifier = Modifier
                .padding(horizontal = 20.dp),
        )

        if (state.signals.isEmpty()) {
            Spacer(modifier = Modifier.weight(EMPTY_STATE_TOP_WEIGHT))

            MomensEmptyView(text = "시그널을 다 확인했어요.")

            Spacer(modifier = Modifier.weight(EMPTY_STATE_BOTTOM_WEIGHT))
        } else {
            SignalList(
                signals = state.signals,
                onSignalClick = {
                    selectedSignal = it
                    onSignalClick(it.id)
                },
                modifier = Modifier.weight(1f),
            )
        }
    }

    selectedSignal?.let { signal ->
        SignalDetailBottomSheet(
            signal = signal,
            evidences = state.evidencesBySignalId[signal.id] ?: persistentListOf(),
            onDismiss = { selectedSignal = null },
            onDeleteClick = {
                onDeleteSignal(signal.id)
                selectedSignal = null
            },
            onRegisterTaskClick = {
                onRegisterTask(signal.id)
                selectedSignal = null
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalScreenPreview() {
    MomensTheme {
        SignalScreen(
            state = SignalState.Fake,
            avatarUrl = null,
            paddingValues = PaddingValues(),
            onSignalClick = {},
            onDeleteSignal = {},
            onRegisterTask = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalScreenEmptyPreview() {
    MomensTheme {
        SignalScreen(
            state = SignalState(),
            avatarUrl = null,
            paddingValues = PaddingValues(),
            onSignalClick = {},
            onDeleteSignal = {},
            onRegisterTask = {},
        )
    }
}
