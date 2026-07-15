package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensChipButtonType
import com.momens.android.data.brief.model.BriefSignalSummaryFilterModel
import com.momens.android.presentation.brief.extension.toFilterType

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

fun BriefSignalSummaryFilterModel.toUiModel(): BriefSignalSummaryFilter {
    val type = key.toFilterType()

    return BriefSignalSummaryFilter(
        type = type,
        label = label.ifBlank { type.name.lowercase().replaceFirstChar(Char::uppercaseChar) },
        count = count,
    )
}
