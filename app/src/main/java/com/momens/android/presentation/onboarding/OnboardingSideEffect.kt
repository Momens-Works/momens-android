package com.momens.android.presentation.onboarding

sealed interface OnboardingSideEffect {
    data object NavigateToSignal : OnboardingSideEffect
}
