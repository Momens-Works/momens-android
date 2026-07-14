package com.momens.android.presentation.project.taskdetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.momens.android.presentation.project.taskdetail.navigation.TaskDetail
import com.momens.android.presentation.project.taskdetail.state.TaskDetailSideEffect
import com.momens.android.presentation.project.taskdetail.state.TaskDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    val taskId: String = savedStateHandle.toRoute<TaskDetail>().taskId

    private val _state = MutableStateFlow(TaskDetailState.Fake)
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<TaskDetailSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun toggleChecklistItem(itemId: String, completed: Boolean) {
        val current = _state.value.taskDetail ?: return

        val updatedItems = current.checklist.items
            .map { item ->
                if (item.id == itemId)
                    item.copy(completed = completed) else item
            }.toPersistentList()

        _state.update { state ->
            state.copy(
                taskDetail = current.copy(
                    checklist = current.checklist.copy(
                        completedCount = updatedItems.count { item -> item.completed },
                        items = updatedItems,
                    ),
                ),
            )
        }
    }
}
