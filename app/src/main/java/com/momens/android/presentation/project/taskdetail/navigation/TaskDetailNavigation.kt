package com.momens.android.presentation.project.taskdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.Route
import com.momens.android.presentation.project.model.Assignee
import com.momens.android.presentation.project.taskdetail.TaskDetailRoute
import com.momens.android.presentation.project.taskdetail.model.TaskDetailModel
import com.momens.android.presentation.project.taskedit.model.TaskEditPayload
import com.momens.android.presentation.project.taskedit.model.toPayloadJson
import com.momens.android.presentation.project.taskedit.navigation.TaskEdit
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
    navigateToTaskEdit: (TaskDetailModel) -> Unit,
) {
    composable<TaskDetail> {
        TaskDetailRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp,
            navigateToTaskEdit = navigateToTaskEdit,
        )
    }
}

internal fun TaskDetailModel.toTaskEditArgs(): TaskEdit = TaskEdit(
    taskId = id,
    title = title,
    role = role,
    priority = priority,
    status = status,
    purpose = purpose,
    payloadJson = TaskEditPayload(
        assignee = assignee?.let { Assignee(id = it.id, name = it.name, url = it.avatarUrl) },
        checklist = checklist.items,
    ).toPayloadJson(),
)
