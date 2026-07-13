package com.momens.android.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momens.android.core.designsystem.component.logo.MomensLogo
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val SPLASH_DELAY_MILLIS = 1_500L

@Composable
fun SplashRoute(
    paddingValues: PaddingValues,
    navigateToSignal: () -> Unit,
    navigateToSignIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        delay(SPLASH_DELAY_MILLIS.milliseconds)
        navigateToSignIn()
    }

    SplashScreen(
        paddingValues = paddingValues,
    )
}

@Composable
private fun SplashScreen(
    paddingValues: PaddingValues,
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

        Spacer(modifier = Modifier.weight(382f))
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    MomensTheme {
        Scaffold { innerPadding ->
            SplashScreen(
                paddingValues = innerPadding,
            )
        }
    }
}
