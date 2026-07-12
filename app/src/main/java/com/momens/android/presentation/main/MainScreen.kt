package com.momens.android.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.designsystem.component.snackbar.MomensSnackbar
import com.momens.android.core.designsystem.effect.momensNavBlurSource
import com.momens.android.core.designsystem.effect.rememberMomensNavBlurState
import com.momens.android.core.designsystem.trigger.GlobalUiEventHolder
import com.momens.android.core.designsystem.trigger.LocalGlobalUiEventTrigger
import com.momens.android.core.designsystem.trigger.rememberGlobalSnackbarController
import com.momens.android.presentation.main.component.MomensMainTabBar
import com.momens.android.presentation.main.navigation.MainAppState
import com.momens.android.presentation.main.navigation.MomensNavHost
import com.momens.android.presentation.main.navigation.rememberMainAppState
import com.momens.android.presentation.main.type.MainTab
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
) {
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()
    val tabs = remember { MainTab.entries.toImmutableList() }
    val navBlurState = rememberMomensNavBlurState()

    val snackbarController = rememberGlobalSnackbarController()
    val eventHolder = remember(snackbarController) {
        GlobalUiEventHolder(showSnackbar = snackbarController::show)
    }

    CompositionLocalProvider(
        LocalGlobalUiEventTrigger provides eventHolder,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier
                    .momensNavBlurSource(state = navBlurState),
                snackbarHost = {
                    SnackbarHost(hostState = snackbarController.snackbarHostState) {
                        val state = snackbarController.currentState ?: return@SnackbarHost

                        MomensSnackbar(
                            content = state.content,
                            modifier = Modifier
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    bottom = state.bottomPadding,
                                )
                                .navigationBarsPadding(),
                        )
                    }
                },
            ) { innerPadding ->
                MomensNavHost(
                    appState = appState,
                    paddingValues = innerPadding,
                )
            }

            currentTab?.let { tab ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .navigationBarsPadding()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    MomensMainTabBar(
                        tabs = tabs,
                        selectedTab = tab,
                        onTabClick = appState::navigate,
                        navBlurState = navBlurState,
                    )
                }
            }
        }
    }
}
