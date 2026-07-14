package com.momens.android.presentation.splash.state

sealed interface SplashSideEffect {
    data object NavigateToSignIn : SplashSideEffect
    data object NavigateToSignal : SplashSideEffect
}
