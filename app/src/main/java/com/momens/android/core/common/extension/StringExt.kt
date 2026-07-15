package com.momens.android.core.common.extension

import java.time.Duration
import java.time.OffsetDateTime

fun String?.toRelativeTimeText(): String {
    if (this.isNullOrBlank()) return "-"
    return runCatching {
        val occurredAt = OffsetDateTime.parse(this)
        val minutes = Duration.between(occurredAt, OffsetDateTime.now()).toMinutes()
        when {
            minutes < 1 -> "방금 전"
            minutes < 60 -> "${minutes}분 전"
            minutes < 60 * 24 -> "${minutes / 60}시간 전"
            else -> "${minutes / (60 * 24)}일 전"
        }
    }.getOrDefault("-")
}
