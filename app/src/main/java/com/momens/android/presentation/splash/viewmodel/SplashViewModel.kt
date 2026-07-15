package com.momens.android.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.local.TokenManager
import com.momens.android.presentation.splash.state.SplashSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val SPLASH_DELAY_MILLIS = 1_500L

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _sideEffect = MutableSharedFlow<SplashSideEffect>()
    var sideEffect = _sideEffect.asSharedFlow()

    init {
        checkStoredSession()
    }

    private fun checkStoredSession() {
        viewModelScope.launch {
            delay(SPLASH_DELAY_MILLIS.milliseconds)

            val refreshToken = tokenManager.getRefreshToken()

            // 임시 storedSession 로직입니다. 추후 토큰 재발급 api연동 플로우로 바꿀 예쩡
            val sideEffect = if (refreshToken.isNullOrBlank()) {
                SplashSideEffect.NavigateToSignIn
            } else {
                // TODO: refresh API 구현 후 서버에서 토큰을 갱신한 경우에만 이동
                    SplashSideEffect.NavigateToSignal
            }

            _sideEffect.emit(sideEffect)
        }
    }
}
