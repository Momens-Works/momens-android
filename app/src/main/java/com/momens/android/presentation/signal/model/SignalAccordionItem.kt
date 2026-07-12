package com.momens.android.presentation.signal.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.momens.android.R

@Immutable
data class SignalAccordionItem(
    val title: String,
    val value: String,
)

enum class SignalAccordionType(
    val text: String,
    @param: DrawableRes val icon: Int,
) {
    SLACK(
        text = "슬랙",
        icon = R.drawable.ic_slack,
    ),
    GITHUB(
        text = "깃헙",
        icon = R.drawable.ic_github,
    ),
    FIGMA(
        text = "피그마",
        icon = R.drawable.ic_figma,
    ),
    FILE(
        text = "파일",
        icon = R.drawable.ic_file,
    ),
}
