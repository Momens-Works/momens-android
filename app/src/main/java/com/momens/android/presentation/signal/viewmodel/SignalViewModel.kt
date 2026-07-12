package com.momens.android.presentation.signal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.presentation.signal.state.SignalSideEffect
import com.momens.android.presentation.signal.state.SignalState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignalViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(SignalState.Fake)
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignalSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun deleteSignal(signalId: String) {
        removeSignal(signalId)

        viewModelScope.launch {
            _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널이 삭제되었습니다."))
        }
    }

    fun registerTask(signalId: String) {
        removeSignal(signalId)

        viewModelScope.launch {
            _sideEffect.emit(
                SignalSideEffect.ShowActionSnackbar(
                    message = "태스크가 등록되었습니다",
                    description = "'투두'에 추가됨",
                    onAction = {
                        viewModelScope.launch { _sideEffect.emit(SignalSideEffect.NavigateToTask) }
                    },
                ),
            )
        }
    }

    private fun removeSignal(signalId: String) {
        _state.update { currentState ->
            currentState.copy(
                signals = currentState.signals
                    .filterNot { it.id == signalId }
                    .toPersistentList(),
            )
        }
    }
}
