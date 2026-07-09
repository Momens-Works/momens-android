package com.momens.android.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.R
import com.momens.android.core.common.state.UiState
import com.momens.android.core.designsystem.component.button.MomensCtaButton
import com.momens.android.core.designsystem.component.type.MomensCtaType
import com.momens.android.core.designsystem.theme.MomensTheme

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
        SignInTopContent(
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

@Composable
private fun SignInTopContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_login_logo),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "프로젝트 컨텍스트 워크스페이스",
            color = MomensTheme.colors.white,
            style = MomensTheme.typography.bodyB12,
        )
    }
}

@Composable
private fun SignInBottomContent(
    isGoogleLoginEnabled: Boolean,
    onGoogleLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        MomensCtaButton(
            onClick = onGoogleLoginClick,
            type = MomensCtaType.LOGIN,
            enabled = isGoogleLoginEnabled,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Text(
                    text = "Continue with Google",
                    color = MomensTheme.colors.gray700,
                    style = MomensTheme.typography.bodyB16,
                )
            }
        }
        Text(
            text = "계속하면 서비스 약관과 개인정보 처리방침에 동의하게 됩니다.",
            color = MomensTheme.colors.gray200,
            style = MomensTheme.typography.captionM10,
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
