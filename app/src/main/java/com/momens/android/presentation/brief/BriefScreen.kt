package com.momens.android.presentation.brief

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BriefRoute(
    paddingValues: PaddingValues,
) {
    BriefScreen(paddingValues = paddingValues)
}

@Composable
private fun BriefScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Brief",
        modifier = modifier.padding(paddingValues),
    )
}
