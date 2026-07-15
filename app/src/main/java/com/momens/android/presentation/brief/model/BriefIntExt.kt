package com.momens.android.presentation.brief.model

fun Int.toProgressFraction(): Float {
    return if (this > 1) {
        this / 100f
    } else {
        toFloat()
    }.coerceIn(0f, 1f)
}
