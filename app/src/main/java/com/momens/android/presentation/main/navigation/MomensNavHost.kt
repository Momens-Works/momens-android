package com.momens.android.presentation.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.momens.android.presentation.brief.navigation.briefNavGraph
import com.momens.android.presentation.signal.navigation.signalNavGraph
import com.momens.android.presentation.signin.navigation.signInNavGraph
import com.momens.android.presentation.splash.navigation.splashNavGraph
import com.momens.android.presentation.project.taskdetail.navigation.taskDetailNavGraph
import com.momens.android.presentation.project.taskedit.navigation.taskEditNavGraph
import com.momens.android.presentation.project.task.navigation.taskNavGraph

@Composable
fun MomensNavHost(
    appState: MainAppState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    NavHost(
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        navController = appState.navController,
        startDestination = appState.startDestination,
        modifier = modifier,
    ) {
        signalNavGraph(
            paddingValues = paddingValues,
            navigateToTask = appState::navigateToTask,
        )
        briefNavGraph(paddingValues = paddingValues)
        taskNavGraph(
            paddingValues = paddingValues,
            navigateToTaskDetail = appState::navigateToTaskDetail,
        )
        taskDetailNavGraph(paddingValues = paddingValues)
        taskEditNavGraph(
            paddingValues = paddingValues,
            navigateUp = appState::navigateUp,
        )
        signInNavGraph(
            paddingValues = paddingValues,
            navigateToSignal = appState::navigateToSignal,
        )
        splashNavGraph(
            paddingValues = paddingValues,
            navigateToSignal = appState::navigateToSignal,
            navigateToSignIn = appState::navigateToSignIn,
        )
    }
}
