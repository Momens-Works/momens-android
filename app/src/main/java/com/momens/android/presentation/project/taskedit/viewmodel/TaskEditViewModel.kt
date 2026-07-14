package com.momens.android.presentation.project.taskedit.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.momens.android.core.common.extension.toTaskEditPayload
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.presentation.project.model.Assignee
import com.momens.android.presentation.project.model.TaskRole
import com.momens.android.presentation.project.taskedit.model.ChecklistItemState
import com.momens.android.presentation.project.taskedit.navigation.TaskEdit
import com.momens.android.presentation.project.taskedit.state.TaskEditSideEffect
import com.momens.android.presentation.project.taskedit.state.TaskEditState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args = savedStateHandle.toRoute<TaskEdit>()
    private val argsPayload = args.payloadJson.toTaskEditPayload()

    init {
        Timber.d(
            "TaskEdit args 수신: taskId=${args.taskId}, title=${args.title}, role=${args.role}, " +
                "priority=${args.priority}, status=${args.status}, purpose=${args.purpose}, " +
                "assignee=${argsPayload.assignee}, checklist=${argsPayload.checklist}",
        )
    }

    private val _state = MutableStateFlow(TaskEditState.Fake)
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<TaskEditSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun changeRole(role: TaskRole) {
        _state.update {
            it.copy(task = it.task.copy(role = role))
        }
    }

    fun changePriority(priority: ImportantLevel) {
        _state.update {
            it.copy(task = it.task.copy(priority = priority))
        }
    }

    fun changeStatus(status: MomensStatusEditType) {
        _state.update {
            it.copy(task = it.task.copy(status = status))
        }
    }

    fun addChecklistItem() {
        if (_state.value.task.checklistTotalCount >= 5) {
            viewModelScope.launch {
                _sideEffect.emit(
                    TaskEditSideEffect.ShowSnackBar(
                        message = "항목은 최대 5개까지 등록할 수 있습니다.",
                    ),
                )
            }
            return
        }

        _state.update {
            val newItem = ChecklistItemState(
                id = UUID.randomUUID().toString(),
                title = "",
                completed = false,
            )
            it.copy(
                task = it.task.copy(
                    checklist = it.task.checklist.add(newItem),
                ),
            )
        }
    }

    fun clearChecklistItem(itemId: String) {
        _state.update { state ->
            state.copy(
                task = state.task.copy(
                    checklist = state.task.checklist.removeAll { it.id == itemId },
                ),
            )
        }
    }

    fun updateChecklistTitle(itemId: String, title: String) {
        _state.update { state ->
            state.copy(
                task = state.task.copy(
                    checklist = state.task.checklist.map {
                        if (it.id == itemId) it.copy(title = title) else it
                    }.toPersistentList(),
                ),
            )
        }
    }

    fun changeCheck(itemId: String, checked: Boolean) {
        _state.update { state ->
            state.copy(
                task = state.task.copy(
                    checklist = state.task.checklist.map {
                        if (it.id == itemId) it.copy(completed = checked) else it
                    }.toPersistentList(),
                ),
            )
        }
    }

    fun updateAssignee(assignee: Assignee) {
        _state.update { it.copy(task = it.task.copy(assignee = assignee)) }
    }

    fun removeAssignee() {
        _state.update { it.copy(task = it.task.copy(assignee = null)) }
    }

    fun getAssignees(search: String) {
        // 나중에 API 연결
    }

    fun saveTask(title: String, purpose: String) {
        _state.update {
            it.copy(task = it.task.copy())
        }
        // 나중에 API 연결

        viewModelScope.launch {
            _sideEffect.emit(TaskEditSideEffect.NavigateUp)
        }
    }
}
