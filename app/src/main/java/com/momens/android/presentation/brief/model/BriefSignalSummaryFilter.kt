package com.momens.android.presentation.brief.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensChipButtonType
import com.momens.android.data.brief.remote.dto.response.BriefSignalSummaryFilterResponse

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

fun BriefSignalSummaryFilterType.toApiFilter(): String? {
    return if (this == BriefSignalSummaryFilterType.ALL) {
        null
    } else {
        name.lowercase()
    }
}

fun BriefSignalSummaryFilterResponse.toUiModel(): BriefSignalSummaryFilter {
    val type = key.toFilterType()

    return BriefSignalSummaryFilter(
        type = type,
        label = label.ifBlank { type.name.lowercase().replaceFirstChar(Char::uppercaseChar) },
        count = count,
    )
}

fun String.toFilterType(): BriefSignalSummaryFilterType {
    return BriefSignalSummaryFilterType.entries.firstOrNull { filterType ->
        filterType.name.equals(this, ignoreCase = true)
    } ?: BriefSignalSummaryFilterType.ALL
}
