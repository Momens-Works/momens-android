package com.momens.android.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainRoute(
    paddingValues: PaddingValues,
) {
    MainScreen(paddingValues = paddingValues)
}

@Composable
private fun MainScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Main",
        modifier = modifier.padding(paddingValues),
    )
}
