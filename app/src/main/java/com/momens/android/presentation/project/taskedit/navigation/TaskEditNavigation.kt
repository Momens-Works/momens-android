package com.momens.android.presentation.project.taskedit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.Route
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.presentation.project.taskedit.TaskEditRoute
import com.momens.android.presentation.project.taskedit.model.AssigneeInfo
import com.momens.android.presentation.project.taskedit.model.TaskRole
import kotlinx.serialization.Serializable

@Serializable
data class TaskEdit(
    val taskId: String,
    val title: String,
    val role: TaskRole,
    val assignee: AssigneeInfo?,
    val priority: ImportantLevel,
    val status: MomensStatusEditType,
    val purpose: String?,
    val checklist: List<TaskEditChecklistItem>,
) : Route

@Serializable
data class TaskEditChecklistItem(
    val itemId: String,
    val title: String,
    val isChecked: Boolean,
)

fun NavController.navigateToTaskEdit(
    taskEdit: TaskEdit,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = taskEdit,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.taskEditNavGraph(
    paddingValues: PaddingValues,
) {
    composable<TaskEdit> {
        TaskEditRoute(
            paddingValues = paddingValues,
        )
    }
}
