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

/**
 * [state]가 [UiState.Loading]으로 바뀌면 최소 [minDurationMs]만큼은 로딩 상태를 유지한 뒤
 * 실제 상태로 전환해서 반환합니다. 로딩이 너무 짧게 끝나 화면이 깜빡이는 현상을 방지합니다.
 *
 * 사용 예:
 * ```
 * val renderState = rememberMinDurationUiState(state)
 * when (renderState) {
 *     UiState.Loading -> ...
 *     ...
 * }
 * ```
 */
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
