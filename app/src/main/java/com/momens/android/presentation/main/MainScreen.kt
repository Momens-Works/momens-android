package com.momens.android.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.momens.android.core.designsystem.effect.momensNavBlurSource
import com.momens.android.core.designsystem.effect.rememberMomensNavBlurState
import com.momens.android.presentation.brief.navigation.briefNavGraph
import com.momens.android.presentation.main.component.MomensMainTabBar
import com.momens.android.presentation.main.component.MomensMainTabBarItemUiModel
import com.momens.android.presentation.main.type.MainTab
import com.momens.android.presentation.signal.navigation.signalNavGraph
import com.momens.android.presentation.signin.navigation.signInNavGraph
import com.momens.android.presentation.splash.navigation.splashNavGraph
import com.momens.android.presentation.task.detail.navigation.taskDetailNavGraph
import com.momens.android.presentation.task.navigation.taskNavGraph
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
) {
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()
    val tabs = remember { MainTab.entries.toImmutableList() }
    val navBlurState = rememberMomensNavBlurState()

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.momensNavBlurSource(state = navBlurState),
        ) { innerPadding ->
            NavHost(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None },
                navController = appState.navController,
                startDestination = appState.startDestination,
            ) {
                signalNavGraph(paddingValues = innerPadding)
                briefNavGraph(paddingValues = innerPadding)
                taskNavGraph(paddingValues = innerPadding)
                taskDetailNavGraph(paddingValues = innerPadding)
                signInNavGraph(paddingValues = innerPadding)
                splashNavGraph(paddingValues = innerPadding)
            }
        }

        currentTab?.let { tab ->
            val tabItems = remember(tabs, tab) {
                tabs.map { mainTab ->
                    MomensMainTabBarItemUiModel(
                        key = mainTab,
                        label = mainTab.label,
                        iconRes = mainTab.iconRes,
                        isSelected = mainTab == tab,
                    )
                }.toImmutableList()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center,
            ) {
                MomensMainTabBar(
                    tabs = tabItems,
                    onTabClick = appState::navigate,
                    navBlurState = navBlurState,
                )
            }
        }
    }
}
