package com.momens.android.presentation.signal.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.momens.android.core.common.navigation.MainTabRoute
import com.momens.android.presentation.signal.SignalRoute
import kotlinx.serialization.Serializable

@Serializable
data object Signal : MainTabRoute

fun NavController.navigateToSignal(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = Signal,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.signalNavGraph(
    paddingValues: PaddingValues,
    navigateToTaskDetail: (String) -> Unit,
) {
    composable<Signal> {
        SignalRoute(
            paddingValues = paddingValues,
            navigateToTaskDetail = navigateToTaskDetail,
        )
    }
}
