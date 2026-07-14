package com.momens.android.presentation.project.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.data.project.task.repository.TaskRepository
import com.momens.android.presentation.project.task.mapper.toItemData
import com.momens.android.presentation.project.task.mapper.toRequestValue
import com.momens.android.presentation.project.task.mapper.toUiState
import com.momens.android.presentation.project.task.model.MomensTaskButtonType
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
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<TaskSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        loadTaskBoard()
    }

    private fun loadTaskBoard() {
        viewModelScope.launch {
            taskRepository.getTaskBoard(TEST_PROJECT_ID)
                .onSuccess { board ->
                    val content = board.toUiState()
                    _uiState.update { state ->
                        state.copy(
                            title = content.title,
                            description = content.description,
                            sections = content.sections,
                        )
                    }
                }
                .onFailure {
                    _sideEffect.emit(TaskSideEffect.ShowSnackbar("태스크를 불러오지 못했습니다"))
                }
        }
    }

    fun addTask(
        title: String,
        role: MomensTaskButtonType,
        priority: ImportantLevel,
    ) {
        viewModelScope.launch {
            taskRepository.createTask(
                projectId = TEST_PROJECT_ID,
                title = title,
                role = role.toRequestValue(),
                priority = priority.toRequestValue(),
            ).onSuccess { created ->
                val newTask = created.toItemData()

                _uiState.update { state ->
                    state.copy(
                        sections = state.sections.map { section ->
                            if (section.type == MomensStatusEditType.TODO) {
                                section.copy(tasks = (section.tasks + newTask).toPersistentList())
                            } else {
                                section
                            }
                        }.toPersistentList(),
                    )
                }

                _sideEffect.emit(
                    TaskSideEffect.ShowActionSnackbar(
                        message = "태스크가 등록되었습니다",
                        description = "'투두'에 추가됨",
                        taskId = newTask.id,
                    ),
                )
            }.onFailure {
                _sideEffect.emit(TaskSideEffect.ShowSnackbar("태스크 등록에 실패했습니다"))
            }
        }
    }

    companion object {
        // TODO: 프로젝트 선택/네비게이션 인자 연동 후 실제 projectId로 교체 (현재 테스트용 하드코딩)
        private const val TEST_PROJECT_ID = "a0000000-0000-4000-8000-000000000003"
    }
}
