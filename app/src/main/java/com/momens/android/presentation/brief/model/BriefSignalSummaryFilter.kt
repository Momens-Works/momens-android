package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensChipButtonType

enum class BriefSignalSummaryFilterType(
    val key: String,
    val chipButtonType: MomensChipButtonType,
) {
    ALL(
        key = "all",
        chipButtonType = MomensChipButtonType.BLACK,
    ),
    DECISIONS(
        key = "decisions",
        chipButtonType = MomensChipButtonType.PURPLE,
    ),
    RISKS(
        key = "risks",
        chipButtonType = MomensChipButtonType.RED,
    ),
    QUESTIONS(
        key = "questions",
        chipButtonType = MomensChipButtonType.MINT,
    ),
    CHANGES(
        key = "changes",
        chipButtonType = MomensChipButtonType.YELLOW,
    ),
}

@Immutable
data class BriefSignalSummaryFilter(
    val type: BriefSignalSummaryFilterType,
    val label: String,
    val count: Int,
)
