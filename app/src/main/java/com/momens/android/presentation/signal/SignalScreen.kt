package com.momens.android.presentation.signal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignalRoute(
    paddingValues: PaddingValues,
) {
    SignalScreen(paddingValues = paddingValues)
}

@Composable
private fun SignalScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Signal",
        modifier = modifier.padding(paddingValues),
    )
}
