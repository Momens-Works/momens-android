package com.momens.android.presentation.signin

sealed interface SignInSideEffect {
    data object LaunchGoogleSignIn : SignInSideEffect

    data object NavigateToSignal : SignInSideEffect
}
