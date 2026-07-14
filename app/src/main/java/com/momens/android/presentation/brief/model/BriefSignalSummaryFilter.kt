package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensChipButtonType

enum class BriefSignalSummaryFilterType(
    val chipButtonType: MomensChipButtonType,
) {
    ALL(MomensChipButtonType.BLACK),
    CHANGE(MomensChipButtonType.YELLOW),
    DECISION(MomensChipButtonType.PURPLE),
    QUESTION(MomensChipButtonType.MINT),
    RISK(MomensChipButtonType.RED),
}

@Immutable
data class BriefSignalSummaryFilter(
    val type: BriefSignalSummaryFilterType,
    val label: String,
    val count: Int,
) {
    val chipButtonType: MomensChipButtonType
        get() = type.chipButtonType
}
