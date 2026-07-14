package com.momens.android.presentation.project.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.momens.android.presentation.project.taskdetail.navigation.TaskDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val taskId: String = savedStateHandle.toRoute<TaskDetail>().taskId
}
