package com.momens.android.presentation.brief.model

fun String.toFilterType(): BriefSignalSummaryFilterType {
    return BriefSignalSummaryFilterType.entries.firstOrNull { filterType ->
        filterType.name.equals(this, ignoreCase = true)
    } ?: BriefSignalSummaryFilterType.ALL
}
