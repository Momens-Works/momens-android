package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensChipButtonType

object BriefSignalSummaryFilterKey {
    const val ALL = "all"
    const val CHANGE = "change"
    const val DECISION = "decision"
    const val QUESTION = "question"
    const val RISK = "risk"
}

@Immutable
data class BriefSignalSummaryFilter(
    val key: String,
    val label: String,
    val count: Int,
) {
    val chipButtonType: MomensChipButtonType
        get() = when (key) {
            BriefSignalSummaryFilterKey.ALL -> MomensChipButtonType.BLACK
            BriefSignalSummaryFilterKey.CHANGE -> MomensChipButtonType.YELLOW
            BriefSignalSummaryFilterKey.DECISION -> MomensChipButtonType.PURPLE
            BriefSignalSummaryFilterKey.QUESTION -> MomensChipButtonType.MINT
            BriefSignalSummaryFilterKey.RISK -> MomensChipButtonType.RED
            else -> MomensChipButtonType.BLACK
        }
}
