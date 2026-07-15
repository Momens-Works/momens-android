package com.momens.android.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.main.component.MomensMainTabBar
import com.momens.android.presentation.main.type.MainTab
import com.momens.android.presentation.onboarding.component.CoachmarkOverlay
import com.momens.android.presentation.onboarding.component.OnBoardingSignalContent
import kotlinx.collections.immutable.persistentListOf

@Composable
fun OnBoardingRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    onCoachmarkFinished: () -> Unit = {},
) {
    var step by remember { mutableStateOf(OnBoardingCoachmarkStep.SignalTitle) }

    OnBoardingScreen(
        step = step,
        paddingValues = paddingValues,
        onNextClick = {
            when (step) {
                OnBoardingCoachmarkStep.SignalTitle -> {
                    step = OnBoardingCoachmarkStep.SignalCard
                }

                OnBoardingCoachmarkStep.SignalCard -> {
                    step = OnBoardingCoachmarkStep.MinsuSuggestion
                }

                OnBoardingCoachmarkStep.MinsuSuggestion -> {
                    onCoachmarkFinished()
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
fun OnBoardingScreen(
    step: OnBoardingCoachmarkStep,
    paddingValues: PaddingValues,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var signalTitleBounds by remember { mutableStateOf<Rect?>(null) }
    var signalCardBounds by remember { mutableStateOf<Rect?>(null) }
    var minsuSuggestionBounds by remember { mutableStateOf<Rect?>(null) }
    val targetBounds = when (step) {
        OnBoardingCoachmarkStep.SignalTitle -> signalTitleBounds
        OnBoardingCoachmarkStep.SignalCard -> signalCardBounds
        OnBoardingCoachmarkStep.MinsuSuggestion -> minsuSuggestionBounds
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MomensTheme.colors.uiBg)
            .padding(paddingValues),
    ) {
        OnBoardingSignalContent(
            onSignalTitlePositioned = { signalTitleBounds = it },
            onSignalCardPositioned = { signalCardBounds = it },
            onMinsuSuggestionPositioned = { minsuSuggestionBounds = it },
        )

        Spacer(Modifier.height(20.dp))

        MomensMainTabBar(
            tabs = persistentListOf(
                MainTab.SIGNAL,
                MainTab.BRIEF,
                MainTab.TASK
            ),
            selectedTab = MainTab.SIGNAL,
            onTabClick = {},
            modifier = Modifier
                .navigationBarsPadding()
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
        )

        CoachmarkOverlay(
            step = step,
            targetBounds = targetBounds,
            onNextClick = onNextClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingScreenPreview() {
    var step by remember { mutableStateOf(OnBoardingCoachmarkStep.SignalTitle) }

    MomensTheme {
        OnBoardingScreen(
            step = step,
            paddingValues = PaddingValues(),
            onNextClick = {
                step = when (step) {
                    OnBoardingCoachmarkStep.SignalTitle -> OnBoardingCoachmarkStep.SignalCard
                    OnBoardingCoachmarkStep.SignalCard -> OnBoardingCoachmarkStep.MinsuSuggestion
                    OnBoardingCoachmarkStep.MinsuSuggestion -> OnBoardingCoachmarkStep.SignalTitle
                }
            },
        )
    }
}
