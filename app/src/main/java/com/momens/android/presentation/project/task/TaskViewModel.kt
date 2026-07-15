package com.momens.android.presentation.project.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.presentation.project.task.model.MomensTaskButtonType
import com.momens.android.presentation.project.task.model.TaskItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

@HiltViewModel
class TaskViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState.Fake)
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<TaskSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun addTask(
        title: String,
        role: MomensTaskButtonType,
        priority: ImportantLevel,
    ) {
        val newTask = TaskItemData(
            id = UUID.randomUUID().toString(),
            title = title,
            role = role,
            priority = priority,
            materialCount = 0,
            tone = ImportantTone.WHITE,
        )

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

        viewModelScope.launch {
            _sideEffect.emit(
                TaskSideEffect.ShowActionSnackbar(
                    message = "태스크가 등록되었습니다",
                    description = "'투두'에 추가됨",
                    taskId = newTask.id,
                ),
            )
        }
    }
}
