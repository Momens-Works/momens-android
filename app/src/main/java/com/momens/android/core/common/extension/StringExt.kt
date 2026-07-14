package com.momens.android.core.common.extension

import com.momens.android.presentation.project.taskedit.model.TaskEditPayload
import kotlinx.serialization.json.Json

fun String.toTaskEditPayload(): TaskEditPayload =
    runCatching { Json.decodeFromString<TaskEditPayload>(this) }.getOrDefault(TaskEditPayload())
