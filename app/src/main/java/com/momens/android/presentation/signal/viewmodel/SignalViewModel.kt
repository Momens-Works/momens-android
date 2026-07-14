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
        loadSignals()
    }

    private fun loadSignals() {
        viewModelScope.launch {
            signalRepository.getSignals(projectId = "a0000000-0000-4000-8000-000000000003")
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
}
