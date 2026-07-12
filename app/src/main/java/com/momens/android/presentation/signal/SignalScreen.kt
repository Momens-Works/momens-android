package com.momens.android.presentation.signal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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
    navigateToTask: () -> Unit = {},
    viewModel: SignalViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val globalTrigger = LocalGlobalUiEventTrigger.current

    viewModel.sideEffect.collectSideEffect {
        when (it) {
            is SignalSideEffect.ShowActionSnackbar -> {
                globalTrigger.showSnackbar(
                    SnackbarState(
                        content = MomensSnackbarModel(
                            title = it.message,
                            description = it.description,
                            type = MomensSnackbarType.BUTTON,
                            onActionClick = it.onAction,
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

            is SignalSideEffect.NavigateToTask -> {
                navigateToTask()
            }
        }
    }

    SignalScreen(
        state = state,
        paddingValues = paddingValues,
        onDeleteSignal = viewModel::deleteSignal,
        onRegisterTask = viewModel::registerTask,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignalScreen(
    state: SignalState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    onDeleteSignal: (Long) -> Unit = {},
    onRegisterTask: (Long) -> Unit = {},
) {
    var selectedSignal by remember { mutableStateOf<SignalCardUiModel?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        MomensDefaultHeader(onProfileClick = {})

        Spacer(modifier = Modifier.height(12.dp))

        MomensPageTitle(
            title = "오늘 확인해야 할 시그널",
            subtitle = "프로젝트의 의사결정에 영향을 줄 수 있는 변화입니다.",
            modifier = Modifier
                .padding(horizontal = 20.dp),
        )

        if (state.signals.isEmpty()) {
            Spacer(modifier = Modifier.weight(80f))

            MomensEmptyView(text = "시그널을 다 확인했어요.")

            Spacer(modifier = Modifier.weight(270f))
        } else {
            SignalList(
                signals = state.signals,
                onSignalClick = { selectedSignal = it },
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
            paddingValues = PaddingValues(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalScreenEmptyPreview() {
    MomensTheme {
        SignalScreen(
            state = SignalState(),
            paddingValues = PaddingValues(),
        )
    }
}
