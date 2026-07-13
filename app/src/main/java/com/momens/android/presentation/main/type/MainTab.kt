package com.momens.android.presentation.main.type

import androidx.annotation.DrawableRes
import com.momens.android.R
import com.momens.android.core.common.navigation.MainTabRoute
import com.momens.android.presentation.brief.navigation.Brief
import com.momens.android.presentation.signal.navigation.Signal
import com.momens.android.presentation.project.task.navigation.Task

enum class MainTab(
    val label: String,
    @param:DrawableRes val iconRes: Int,
    val route: MainTabRoute,
) {
    SIGNAL(
        label = "시그널",
        iconRes = R.drawable.ic_signal,
        route = Signal,
    ),

    BRIEF(
        label = "브리프",
        iconRes = R.drawable.ic_brief,
        route = Brief,
    ),

    TASK(
        label = "태스크",
        iconRes = R.drawable.ic_task,
        route = Task,
    );

    companion object {
        fun find(predicate: (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        fun contains(predicate: (MainTabRoute) -> Boolean): Boolean {
            return entries.any { predicate(it.route) }
        }
    }
}
