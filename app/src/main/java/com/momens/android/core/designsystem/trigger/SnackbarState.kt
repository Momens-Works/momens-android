package com.momens.android.core.designsystem.trigger

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.snackbar.model.MomensSnackbarModel

@Immutable
data class SnackbarState(
    val content: MomensSnackbarModel,
)
