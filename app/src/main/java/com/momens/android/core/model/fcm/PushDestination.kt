package com.momens.android.core.model.fcm


enum class PushDestination(
    val raw: String,
) {
    SIGNAL_DETAIL(raw = "signal_detail"),

    UNKNOWN(raw = "unknown"),
    ;

    companion object {
        fun fromRaw(raw: String?): PushDestination {
            return entries.find { it.raw == raw } ?: UNKNOWN
        }
    }
}
