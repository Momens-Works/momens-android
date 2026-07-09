package com.momens.android.presentation.task.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.MainTabRoute
import com.momens.android.presentation.task.TaskRoute
import kotlinx.serialization.Serializable

@Serializable
data object Task : MainTabRoute

fun NavController.navigateToTask(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = Task,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.taskNavGraph(
    paddingValues: PaddingValues,
) {
    composable<Task> {
        TaskRoute(
            paddingValues = paddingValues,
        )
    }
}
