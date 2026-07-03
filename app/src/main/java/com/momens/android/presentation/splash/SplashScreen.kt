package com.momens.android.presentation.splash

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashRoute(
    paddingValues: PaddingValues,
) {
    SplashScreen(paddingValues = paddingValues)
}

@Composable
private fun SplashScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Splash",
        modifier = modifier.padding(paddingValues),
    )
}
