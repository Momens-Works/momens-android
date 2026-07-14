package com.momens.android.presentation.project.taskdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.Route
import com.momens.android.presentation.project.taskdetail.TaskDetailRoute
import com.momens.android.presentation.project.taskdetail.model.TaskDetailModel
import com.momens.android.presentation.project.taskedit.model.AssigneeInfo
import com.momens.android.presentation.project.taskedit.navigation.TaskEdit
import com.momens.android.presentation.project.taskedit.navigation.TaskEditChecklistItem
import kotlinx.serialization.Serializable

@Serializable
data object TaskDetail : Route

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

fun TaskDetailModel.toTaskEditArgs(): TaskEdit = TaskEdit(
    taskId = id,
    title = title,
    role = role,
    assignee = assignee?.let { AssigneeInfo(id = it.id, name = it.name, url = it.avatarUrl) },
    priority = priority,
    status = status,
    purpose = purpose,
    checklist = checklist.items.map { item ->
        TaskEditChecklistItem(itemId = item.id, title = item.title, isChecked = item.completed)
    },
)
