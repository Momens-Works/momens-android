package com.momens.android.core.designsystem.theme

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LocalMomensColorProvider = staticCompositionLocalOf {
    defaultMomensColors
}

val LocalMomensTypographyProvider = staticCompositionLocalOf {
    defaultMomensTypography
}

@Composable
fun MomensTheme(
    colors: MomensColors = defaultMomensColors,
    typography: MomensTypography = defaultMomensTypography,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val activity = LocalActivity.current
    if (!view.isInEditMode) {
        SideEffect {
            activity?.window?.let { window ->
                WindowCompat.getInsetsController(window, view)
                    .isAppearanceLightStatusBars = true
            }
        }
    }

    CompositionLocalProvider(
        LocalMomensColorProvider provides colors,
        LocalMomensTypographyProvider provides typography
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object MomensTheme {
    val colors: MomensColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMomensColorProvider.current
    val typography: MomensTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMomensTypographyProvider.current
}
