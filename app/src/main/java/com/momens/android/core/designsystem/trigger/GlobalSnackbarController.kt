package com.momens.android.core.designsystem.trigger

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val SNACKBAR_AUTO_DISMISS_MILLIS = 2_000L

@Stable
class GlobalSnackbarController(
    private val scope: CoroutineScope,
) {
    val snackbarHostState = SnackbarHostState()

    var currentState by mutableStateOf<SnackbarState?>(null)
        private set

    private var showJob: Job? = null
    private var timerJob: Job? = null

    fun show(state: SnackbarState) {
        val originalOnActionClick = state.content.onActionClick
        currentState = state.copy(
            content = state.content.copy(
                onActionClick = {
                    originalOnActionClick()
                    dismiss()
                },
            ),
        )

        scope.launch {
            clearCurrent()

            val job = launch {
                snackbarHostState.showSnackbar(message = state.content.title)
            }
            showJob = job

            timerJob = launch {
                delay(duration = SNACKBAR_AUTO_DISMISS_MILLIS.milliseconds)
                if (showJob == job) {
                    job.cancel()
                    currentState = null
                    showJob = null
                    timerJob = null
                }
            }
        }
    }

    fun dismiss() {
        clearCurrent()
        currentState = null
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    private fun clearCurrent() {
        showJob?.cancel()
        timerJob?.cancel()
        snackbarHostState.currentSnackbarData?.dismiss()
    }
}

@Composable
fun rememberGlobalSnackbarController(
    scope: CoroutineScope = rememberCoroutineScope(),
): GlobalSnackbarController = remember { GlobalSnackbarController(scope) }
