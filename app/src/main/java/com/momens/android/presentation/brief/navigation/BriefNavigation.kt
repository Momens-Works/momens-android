package com.momens.android.presentation.brief.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.Route
import com.momens.android.presentation.brief.BriefRoute
import kotlinx.serialization.Serializable

@Serializable
data object Brief : Route

fun NavController.navigateToBrief(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = Brief,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.briefNavGraph(
    paddingValues: PaddingValues,
) {
    composable<Brief> {
        BriefRoute(
            paddingValues = paddingValues,
        )
    }
}
