package com.momens.android.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.momens.android.presentation.brief.navigation.navigateToBrief
import com.momens.android.presentation.main.type.MainTab
import com.momens.android.presentation.project.task.navigation.navigateToTask
import com.momens.android.presentation.project.taskdetail.navigation.navigateToTaskDetail
import com.momens.android.presentation.project.taskedit.navigation.TaskEdit
import com.momens.android.presentation.project.taskedit.navigation.navigateToTaskEdit
import com.momens.android.presentation.signal.navigation.navigateToSignal
import com.momens.android.presentation.signin.navigation.SignIn
import com.momens.android.presentation.signin.navigation.navigateToSignIn
import com.momens.android.presentation.splash.navigation.navigateToSplash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class MainAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val startDestination = SignIn

    private val clearStackNavOptions = navOptions {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
        restoreState = false
    }

    private val keepStackNavOptions = navOptions {
        launchSingleTop = true
        restoreState = true
    }

    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            MainTab.find { route ->
                destination?.hasRoute(route::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun navigate(tab: MainTab) {
        if (currentTab.value == tab) return

        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }

        when (tab) {
            MainTab.SIGNAL -> navController.navigateToSignal(navOptions = navOptions)
            MainTab.BRIEF -> navController.navigateToBrief(navOptions = navOptions)
            MainTab.TASK -> navController.navigateToTask(navOptions = navOptions)
        }
    }

    fun navigateToSplash(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToSplash(navOptions = navOptions)
    }

    fun navigateToSignIn(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToSignIn(navOptions = navOptions)
    }

    fun navigateToBrief(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToBrief(navOptions = navOptions)
    }

    fun navigateToSignal(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToSignal(navOptions = navOptions)
    }

    fun navigateToTask(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToTask(navOptions = navOptions)
    }

    fun navigateToTaskDetail(taskId: String, navOptions: NavOptions? = keepStackNavOptions) {
        navController.navigateToTaskDetail(taskId = taskId, navOptions = navOptions)
    }

    fun navigateToTaskEdit(taskEdit: TaskEdit, navOptions: NavOptions? = keepStackNavOptions) {
        navController.navigateToTaskEdit(taskEdit = taskEdit, navOptions = navOptions)
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MainAppState = remember(navController, coroutineScope) {
    MainAppState(
        navController = navController,
        coroutineScope = coroutineScope,
    )
}
