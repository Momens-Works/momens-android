package com.momens.android.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.momens.android.presentation.brief.navigation.briefNavGraph
import com.momens.android.presentation.main.component.MomensMainTabBar
import com.momens.android.presentation.project.navigation.projectNavGraph
import com.momens.android.presentation.project.task.detail.navigation.taskDetailNavGraph
import com.momens.android.presentation.signal.navigation.signalNavGraph
import com.momens.android.presentation.signin.navigation.signInNavGraph
import com.momens.android.presentation.splash.navigation.splashNavGraph

@Composable
fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
) {
    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            currentTab?.takeIf { isBottomBarVisible }?.let { tab ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    MomensMainTabBar(
                        selectedTab = tab,
                        onTabClick = appState::navigate,
                    )
                }
            }
        },
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
            projectNavGraph(paddingValues = innerPadding)
            taskDetailNavGraph(paddingValues = innerPadding)
            signInNavGraph(paddingValues = innerPadding)
            splashNavGraph(paddingValues = innerPadding)
        }
    }
}
