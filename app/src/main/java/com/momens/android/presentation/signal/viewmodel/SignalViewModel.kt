package com.momens.android.presentation.signal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.local.TokenManager
import com.momens.android.data.signal.repository.SignalRepository
import com.momens.android.presentation.signal.model.toUiModels
import com.momens.android.presentation.signal.state.SignalSideEffect
import com.momens.android.presentation.signal.state.SignalState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignalViewModel @Inject constructor(
    private val signalRepository: SignalRepository,
    private val tokenManager: TokenManager,
) : ViewModel() {
    private val _state = MutableStateFlow(SignalState())
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignalSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            // TODO: 로그인 연동 붙으면 제거. 로그인 API가 아직 없어 Signal API만 임시 토큰으로 호출 확인하는 용도.
            tokenManager.saveTokens(
                accessToken = TEMP_ACCESS_TOKEN,
                refreshToken = TEMP_REFRESH_TOKEN,
            )
            loadSignals()
        }
    }

    private fun loadSignals() {
        viewModelScope.launch {
            signalRepository.getSignals(projectId = TEMP_PROJECT_ID)
                .onSuccess { signalList ->
                    _state.update { currentState ->
                        currentState.copy(
                            pageTitle = signalList.title,
                            pageDescription = signalList.description,
                            signals = signalList.signals.toUiModels(),
                        )
                    }
                }
                .onFailure {
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널을 불러오지 못했습니다."))
                }
        }
    }

    fun onSignalClick(signalId: String) {
        if (_state.value.evidencesBySignalId.containsKey(signalId)) return

        viewModelScope.launch {
            signalRepository.getSignalDetail(signalId = signalId)
                .onSuccess { detail ->
                    _state.update { currentState ->
                        currentState.copy(
                            evidencesBySignalId = currentState.evidencesBySignalId
                                .toPersistentMap()
                                .put(signalId, detail.evidence.toUiModels()),
                        )
                    }
                }
                .onFailure {
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널 상세를 불러오지 못했습니다."))
                }
        }
    }

    fun deleteSignal(signalId: String) {
        viewModelScope.launch {
            signalRepository.dismissSignal(signalId = signalId)
                .onSuccess {
                    loadSignals()
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널이 삭제되었습니다."))
                }
                .onFailure {
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널 삭제에 실패했습니다."))
                }
        }
    }

    fun registerTask(signalId: String) {
        viewModelScope.launch {
            signalRepository.convertToTask(signalId = signalId)
                .onSuccess {
                    loadSignals()
                    _sideEffect.emit(
                        SignalSideEffect.ShowActionSnackbar(
                            message = "태스크가 등록되었습니다",
                            description = "'투두'에 추가됨",
                        ),
                    )
                }
                .onFailure {
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "태스크 등록에 실패했습니다."))
                }
        }
    }

    private companion object {
        // TODO: 프로젝트 선택/세션 기능이 붙으면 실제 현재 프로젝트 id로 교체
        const val TEMP_PROJECT_ID = "a0000000-0000-4000-8000-000000000003"

        // TODO: 실제 로그인 붙으면 제거. 테스트용 accessToken/refreshToken을 여기에 채워서 사용.
        const val TEMP_ACCESS_TOKEN = "PUT_TEST_ACCESS_TOKEN_HERE"
        const val TEMP_REFRESH_TOKEN = "PUT_TEST_REFRESH_TOKEN_HERE"
    }
}
