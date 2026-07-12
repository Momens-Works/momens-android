package com.momens.android.presentation.task.edit.model

import androidx.compose.runtime.Immutable

@Immutable
data class AssigneeInfo(
    val id: String,
    val name: String,
    val url: String?,
)
