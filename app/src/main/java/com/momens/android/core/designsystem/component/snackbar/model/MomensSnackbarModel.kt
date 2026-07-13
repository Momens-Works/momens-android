package com.momens.android.core.designsystem.component.snackbar.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.MomensSnackbarType

@Immutable
data class MomensSnackbarModel(
    val title: String,
    val description: String = "",
    val type: MomensSnackbarType = MomensSnackbarType.DEFAULT,
    val onActionClick: () -> Unit = {},
)
