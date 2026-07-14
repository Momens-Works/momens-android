package com.momens.android.presentation.project.taskdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.Route
import com.momens.android.presentation.project.taskdetail.TaskDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class TaskDetail(
    val taskId: String,
) : Route

fun NavController.navigateToTaskDetail(
    taskId: String,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = TaskDetail(taskId = taskId),
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.taskDetailNavGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToTaskEdit: (String) -> Unit,
) {
    composable<TaskDetail> {
        TaskDetailRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp,
            navigateToTaskEdit = navigateToTaskEdit,
        )
    }
}
