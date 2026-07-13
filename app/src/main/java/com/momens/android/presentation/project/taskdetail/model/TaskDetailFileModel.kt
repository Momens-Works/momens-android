package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class TaskDetailFileModel(
    val id: String,
    val title: String,
    val summary: String,
    val roles: ImmutableList<String>,
    val kind: String,
    val sourceUrl: String,
)
