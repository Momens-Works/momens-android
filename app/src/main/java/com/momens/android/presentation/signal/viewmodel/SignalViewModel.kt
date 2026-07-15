package com.momens.android.presentation.signal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.extension.updateSuccess
import com.momens.android.core.common.state.UiState
import com.momens.android.core.local.project.ProjectManager
import com.momens.android.core.util.successData
import com.momens.android.data.signal.repository.SignalRepository
import com.momens.android.presentation.signal.model.toUiModels
import com.momens.android.presentation.signal.state.SignalSideEffect
import com.momens.android.presentation.signal.state.SignalState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignalViewModel @Inject constructor(
    projectManager: ProjectManager,
    private val signalRepository: SignalRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<SignalState>>(UiState.Loading)
    val state: StateFlow<UiState<SignalState>> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignalSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    val projectContext = projectManager.observeProjectContext().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = projectManager.currentProjectContext,
    )

    fun loadSignals() {
        val projectId = projectContext.value.projectId ?: return

        _state.value = UiState.Loading

        viewModelScope.launch {
            signalRepository.getSignals(projectId = projectId).onSuccess { signalList ->
                    _state.update { currentState ->
                        val currentData = currentState.successData ?: SignalState()
                        UiState.Success(
                            currentData.copy(
                                pageTitle = signalList.title,
                                pageDescription = signalList.description,
                                signals = signalList.signals.toUiModels(),
                            ),
                        )
                    }
                }.onFailure {
                    if (_state.value !is UiState.Success) {
                        _state.value = UiState.Failure
                    }
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널을 불러오지 못했습니다."))
                }
        }
    }

    fun onSignalClick(signalId: String) {
        val currentData = _state.value.successData ?: return
        if (currentData.evidencesBySignalId.containsKey(signalId)) return

        viewModelScope.launch {
            signalRepository.getSignalDetail(signalId = signalId).onSuccess { detail ->
                    _state.updateSuccess { state ->
                        state.copy(
                            evidencesBySignalId = state.evidencesBySignalId.toPersistentMap()
                                .put(signalId, detail.evidence.toUiModels()),
                        )
                    }
                }.onFailure {
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널 상세를 불러오지 못했습니다."))
                }
        }
    }

    fun deleteSignal(signalId: String) {
        viewModelScope.launch {
            signalRepository.dismissSignal(signalId = signalId).onSuccess {
                    loadSignals()
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널이 삭제되었습니다."))
                }.onFailure {
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "시그널 삭제에 실패했습니다."))
                }
        }
    }

    fun registerTask(signalId: String) {
        viewModelScope.launch {
            signalRepository.convertToTask(signalId = signalId).onSuccess {
                    loadSignals()
                    _sideEffect.emit(
                        SignalSideEffect.ShowActionSnackbar(
                            message = "태스크가 등록되었습니다",
                            description = "'투두'에 추가됨",
                        ),
                    )
                }.onFailure {
                    _sideEffect.emit(SignalSideEffect.ShowSnackbar(message = "태스크 등록에 실패했습니다."))
                }
        }
    }
}
