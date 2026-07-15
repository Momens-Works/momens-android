package com.momens.android.presentation.brief.model

fun BriefSignalSummaryFilterType.toApiFilter(): String? {
    return if (this == BriefSignalSummaryFilterType.ALL) {
        null
    } else {
        name.lowercase()
    }
}

fun String.toFilterType(): BriefSignalSummaryFilterType {
    return BriefSignalSummaryFilterType.entries.firstOrNull { filterType ->
        filterType.name.equals(this, ignoreCase = true)
    } ?: BriefSignalSummaryFilterType.ALL
}
