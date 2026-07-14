package com.momens.android.presentation.project.taskedit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.Route
import com.momens.android.presentation.project.taskedit.TaskEditRoute
import kotlinx.serialization.Serializable

@Serializable
data class TaskEdit(
    val taskId: String,
) : Route

fun NavController.navigateToTaskEdit(
    taskId: String,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = TaskEdit(taskId = taskId),
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.taskEditNavGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
) {
    composable<TaskEdit> {
        TaskEditRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp,
        )
    }
}
