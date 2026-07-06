package com.momens.android.presentation.main.type

import androidx.annotation.DrawableRes
import com.momens.android.R

enum class MomensBottomNavigationBarType(
    val label: String,
    @param:DrawableRes val iconRes: Int,
) {
    SIGNAL(
        label = "시그널",
        iconRes = R.drawable.ic_signal,
    ),

    BRIEF(
        label = "브리프",
        iconRes = R.drawable.ic_brief,
    ),

    TASK(
        label = "태스크",
        iconRes = R.drawable.ic_task,
    ),
}
