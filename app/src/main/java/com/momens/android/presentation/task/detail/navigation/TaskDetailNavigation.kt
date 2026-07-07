package com.momens.android.presentation.task.detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.MainTabRoute
import com.momens.android.presentation.task.detail.TaskDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data object TaskDetail : MainTabRoute

fun NavController.navigateToTaskDetail(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = TaskDetail,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.taskDetailNavGraph(
    paddingValues: PaddingValues,
) {
    composable<TaskDetail> {
        TaskDetailRoute(
            paddingValues = paddingValues,
        )
    }
}
