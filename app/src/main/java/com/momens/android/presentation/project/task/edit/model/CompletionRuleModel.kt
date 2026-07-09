package com.momens.android.presentation.project.task.edit.model

data class CompletionRuleModel(
    val id: Long,
    val label: String,
    val completed: Boolean,
    val enabled: Boolean,
)
