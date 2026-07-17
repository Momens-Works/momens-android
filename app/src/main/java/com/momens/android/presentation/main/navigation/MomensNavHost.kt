package com.momens.android.presentation.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.compose.NavHost
import com.momens.android.presentation.brief.navigation.briefNavGraph
import com.momens.android.presentation.onboarding.navigation.onboardingNavGraph
import com.momens.android.presentation.project.task.navigation.taskNavGraph
import com.momens.android.presentation.project.taskdetail.navigation.taskDetailNavGraph
import com.momens.android.presentation.project.taskedit.navigation.TaskEdit
import com.momens.android.presentation.project.taskedit.navigation.taskEditNavGraph
import com.momens.android.presentation.signal.navigation.signalNavGraph
import com.momens.android.presentation.signin.navigation.signInNavGraph
import com.momens.android.presentation.splash.navigation.splashNavGraph

@Composable
fun MomensNavHost(
    appState: MainAppState,
    paddingValues: PaddingValues,
    tabBarHeight: Dp,
    modifier: Modifier = Modifier,
    pendingSignalId: String? = null,
    onPendingSignalConsumed: () -> Unit = {},
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
            pendingSignalId = pendingSignalId,
            onPendingSignalConsumed = onPendingSignalConsumed,
            navigateToTaskDetail = { taskId ->
                appState.navigateToTask()
                appState.navigateToTaskDetail(taskId = taskId)
            },
        )
        briefNavGraph(
            paddingValues = paddingValues,
            tabBarHeight = tabBarHeight,
        )
        taskNavGraph(
            paddingValues = paddingValues,
            navigateToTaskDetail = appState::navigateToTaskDetail,
        )
        taskEditNavGraph(
            paddingValues = paddingValues,
            navigateUp = appState::navigateUp,
        )
        taskDetailNavGraph(
            paddingValues = paddingValues,
            navigateUp = appState::navigateUp,
            navigateToTaskEdit = { taskDetail ->
                appState.navigateToTaskEdit(
                    TaskEdit(
                        taskId = taskDetail.id,
                    ),
                )
            },
        )
        signInNavGraph(
            paddingValues = paddingValues,
            navigateToOnboarding = appState::navigateToOnboarding,
            navigateToSignal = appState::navigateToSignal,
        )
        onboardingNavGraph(
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
