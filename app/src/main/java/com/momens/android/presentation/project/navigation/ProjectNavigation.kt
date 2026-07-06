package com.momens.android.presentation.project.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.Route
import com.momens.android.presentation.project.ProjectRoute
import kotlinx.serialization.Serializable

@Serializable
data object Project : Route

fun NavController.navigateToProject(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = Project,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.projectNavGraph(
    paddingValues: PaddingValues,
) {
    composable<Project> {
        ProjectRoute(
            paddingValues = paddingValues,
        )
    }
}
