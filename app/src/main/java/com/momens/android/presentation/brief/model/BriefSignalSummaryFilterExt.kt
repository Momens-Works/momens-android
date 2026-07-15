package com.momens.android.presentation.brief.model

fun BriefSignalSummaryFilterType.toApiFilter(): String? {
    return if (this == BriefSignalSummaryFilterType.ALL) {
        null
    } else {
        name.lowercase()
    }
}
