package com.momens.android.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.common.state.UiState
import com.momens.android.core.designsystem.component.logo.MomensLogo
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.signin.component.SignInBottomContent

@Composable
fun SignInRoute(
    paddingValues: PaddingValues,
    navigateToSignal: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val signInState = viewModel.signInState.collectAsStateWithLifecycle()

    SignInScreen(
        paddingValues = paddingValues,
        isGoogleLoginEnabled = signInState.value !is UiState.Loading,
        onGoogleLoginClick = { viewModel.signInWithGoogle() }
    )
}

@Composable
private fun SignInScreen(
    paddingValues: PaddingValues,
    isGoogleLoginEnabled: Boolean,
    onGoogleLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MomensTheme.colors.primary100)
            .padding(paddingValues),
    ) {
        MomensLogo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 145.dp),
        )

        SignInBottomContent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 22.dp),
            isGoogleLoginEnabled = isGoogleLoginEnabled,
            onGoogleLoginClick = onGoogleLoginClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    MomensTheme {
        Scaffold { innerPadding ->
            SignInScreen(
                paddingValues = innerPadding,
                isGoogleLoginEnabled = true,
                onGoogleLoginClick = {},
            )
        }
    }
}
