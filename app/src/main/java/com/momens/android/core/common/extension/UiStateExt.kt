package com.momens.android.core.common.extension

import android.os.SystemClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.momens.android.core.common.state.UiState
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val DEFAULT_MIN_LOADING_DURATION_MS = 300L

@Composable
fun <T> rememberMinDurationUiState(
    state: UiState<T>,
    minDurationMs: Long = DEFAULT_MIN_LOADING_DURATION_MS,
): UiState<T> {
    var renderState by remember { mutableStateOf(state) }
    var loadingStartedAt by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(state) {
        if (state is UiState.Loading) {
            if (loadingStartedAt == null) {
                loadingStartedAt = SystemClock.elapsedRealtime()
            }
            renderState = UiState.Loading
        } else {
            val elapsed = loadingStartedAt?.let { SystemClock.elapsedRealtime() - it } ?: minDurationMs
            val remainingDelay = minDurationMs - elapsed
            if (remainingDelay > 0) {
                delay(remainingDelay.milliseconds)
            }
            renderState = state
            loadingStartedAt = null
        }
    }

    return renderState
}
