package com.momens.android.presentation.onboarding.extension

import androidx.compose.ui.geometry.Rect

fun Rect.offsetBy(
    horizontal: Float,
    vertical: Float,
): Rect = Rect(
    left = left + horizontal,
    top = top + vertical,
    right = right + horizontal,
    bottom = bottom + vertical,
)
