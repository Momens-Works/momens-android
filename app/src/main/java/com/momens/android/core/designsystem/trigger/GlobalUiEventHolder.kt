package com.momens.android.core.designsystem.trigger

import androidx.compose.runtime.Stable

@Stable
class GlobalUiEventHolder(
    val showSnackbar: (SnackbarState) -> Unit,
)
