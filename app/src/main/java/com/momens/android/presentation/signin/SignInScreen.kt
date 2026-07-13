package com.momens.android.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.auth.google.rememberGoogleSignInLauncher
import com.momens.android.core.common.extension.collectSideEffect
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
    val signInState by viewModel.signInState.collectAsStateWithLifecycle()
    val googleSignInLauncher = rememberGoogleSignInLauncher()

    viewModel.sideEffect.collectSideEffect { sideEffect ->
        when (sideEffect) {
            SignInSideEffect.LaunchGoogleSignIn -> {
                viewModel.onGoogleSignInResult(
                    googleSignInLauncher.launch(),
                )
            }

            SignInSideEffect.NavigateToSignal -> navigateToSignal()
        }
    }

    SignInScreen(
        paddingValues = paddingValues,
        isGoogleLoginEnabled = signInState !is UiState.Loading,
        onGoogleLoginClick = viewModel::onGoogleSignInClick,
    )
}

@Composable
private fun SignInScreen(
    paddingValues: PaddingValues,
    isGoogleLoginEnabled: Boolean,
    onGoogleLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MomensTheme.colors.primary100)
            .padding(paddingValues),
    ) {
        Spacer(modifier = Modifier.weight(145f))

        MomensLogo(
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.weight(273f))

        SignInBottomContent(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            isGoogleLoginEnabled = isGoogleLoginEnabled,
            onGoogleLoginClick = onGoogleLoginClick,
        )

        Spacer(modifier = Modifier.weight(22f))
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
