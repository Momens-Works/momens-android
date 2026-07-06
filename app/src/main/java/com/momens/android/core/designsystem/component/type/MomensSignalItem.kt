package com.momens.android.core.designsystem.component.type

data class MomensSignalItem(
    val type: MomensSignalType,
    val text: String,
)

enum class MomensSignalType {
    RISK,
    QUESTION,
    DECISION,
    UNKNOWN,
}
