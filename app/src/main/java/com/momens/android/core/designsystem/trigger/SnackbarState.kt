package com.momens.android.core.designsystem.trigger

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.snackbar.model.MomensSnackbarModel

@Immutable
data class SnackbarState(
    val content: MomensSnackbarModel,
    val bottomPadding: Dp = 16.dp,
)
