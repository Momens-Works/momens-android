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
