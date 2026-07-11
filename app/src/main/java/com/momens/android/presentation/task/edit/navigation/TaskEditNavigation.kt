package com.momens.android.presentation.task.detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.MainTabRoute
import com.momens.android.core.common.navigation.Route
import com.momens.android.presentation.task.detail.TaskDetailRoute
import com.momens.android.presentation.task.edit.TaskEditRoute
import kotlinx.serialization.Serializable

@Serializable
data object TaskEdit : Route

fun NavController.navigateToTaskEdit(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = TaskEdit,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.taskEditNavGraph(
    paddingValues: PaddingValues,
) {
    composable<TaskDetail> {
        TaskEditRoute(
            paddingValues = paddingValues,
        )
    }
}
