package com.momens.android.presentation.signin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignInRoute(
    paddingValues: PaddingValues,
) {
    // TODO: 구글 로그인 성공 콜백이 연결되면 Signal로 clear stack 이동합니다.
    // onGoogleSignInSuccess -> navigateToSignal(clearStack)
    SignInScreen(paddingValues = paddingValues)
}

@Composable
private fun SignInScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Sign In",
        modifier = modifier.padding(paddingValues),
    )
}
