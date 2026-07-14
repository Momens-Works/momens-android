package com.momens.android.presentation.project.taskdetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.momens.android.core.common.extension.updateSuccess
import com.momens.android.core.common.state.UiState
import com.momens.android.core.common.util.successData
import com.momens.android.data.project.taskdetail.repository.TaskDetailRepository
import com.momens.android.presentation.project.model.toUiModel
import com.momens.android.presentation.project.taskdetail.model.toUiModel
import com.momens.android.presentation.project.taskdetail.navigation.TaskDetail
import com.momens.android.presentation.project.taskdetail.state.TaskDetailSideEffect
import com.momens.android.presentation.project.taskdetail.state.TaskDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val taskDetailRepository: TaskDetailRepository,
) : ViewModel() {
    val taskId: String = savedStateHandle.toRoute<TaskDetail>().taskId

    private val _state = MutableStateFlow<UiState<TaskDetailState>>(UiState.Loading)
    val state: StateFlow<UiState<TaskDetailState>> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<TaskDetailSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        loadTaskDetail()
    }

    private fun loadTaskDetail() {
        viewModelScope.launch {
            taskDetailRepository.getTaskDetail(taskId = taskId)
                .onSuccess { detail ->
                    _state.update { currentState ->
                        val currentData = currentState.successData ?: TaskDetailState()
                        UiState.Success(
                            currentData.copy(taskDetail = detail.toUiModel()),
                        )
                    }
                }
                .onFailure {
                    if (_state.value !is UiState.Success) {
                        _state.value = UiState.Failure
                    }
                    _sideEffect.emit(TaskDetailSideEffect.ShowSnackbar(message = "태스크를 불러오지 못했습니다."))
                }
        }
    }

    fun toggleChecklistItem(itemId: String, completed: Boolean) {
        viewModelScope.launch {
            taskDetailRepository.updateChecklistItem(
                taskId = taskId,
                itemId = itemId,
                completed = completed,
            )
                .onSuccess { result ->
                    _state.updateSuccess { state ->
                        val current = state.taskDetail ?: return@updateSuccess state

                        val updatedItems = current.checklist.items
                            .map { item ->
                                if (item.id == result.item.id) result.item.toUiModel() else item
                            }
                            .toPersistentList()

                        state.copy(
                            taskDetail = current.copy(
                                checklist = current.checklist.copy(
                                    completedCount = result.completedCount,
                                    totalCount = result.totalCount,
                                    items = updatedItems,
                                ),
                            ),
                        )
                    }
                }
                .onFailure {
                    _sideEffect.emit(TaskDetailSideEffect.ShowSnackbar(message = "완료 상태 변경에 실패했습니다."))
                }
        }
    }
}
