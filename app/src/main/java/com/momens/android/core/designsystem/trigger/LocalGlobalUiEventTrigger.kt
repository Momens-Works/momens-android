package com.momens.android.core.designsystem.trigger

import androidx.compose.runtime.staticCompositionLocalOf

val LocalGlobalUiEventTrigger = staticCompositionLocalOf<GlobalUiEventHolder> {
    error("No GlobalUiEvent Trigger provided")
}
