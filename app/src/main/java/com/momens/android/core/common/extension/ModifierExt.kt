package com.momens.android.core.common.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role

@Composable
inline fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    crossinline onClick: () -> Unit
): Modifier {
    return clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { onClick() },
        enabled = enabled
    )
}

@Composable
inline fun Modifier.noRippleToggleable(
    value: Boolean,
    enabled: Boolean = true,
    role: Role? = null,
    crossinline onValueChange: (Boolean) -> Unit,
): Modifier {
    return toggleable(
        value = value,
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        role = role,
        onValueChange = { onValueChange(it) },
    )
}

fun Modifier.addFocusCleaner(focusManager: FocusManager): Modifier {
    return this.pointerInput(focusManager) {
        detectTapGestures(
            onTap = { focusManager.clearFocus() }
        )
    }
}

@Composable
fun Modifier.advancedImePadding(): Modifier {
    var consumePadding by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    return onGloballyPositioned { coordinates ->
        consumePadding = (
            coordinates.findRootCoordinates().size.height -
                (coordinates.positionInWindow().y + coordinates.size.height).toInt()
            ).coerceAtLeast(
            0
        )
    }
        .consumeWindowInsets(
            PaddingValues(bottom = with(density) { consumePadding.toDp() })
        )
        .imePadding()
}
