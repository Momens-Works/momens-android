package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.TaskSummaryResponse

data class SignalTaskModel(
    val id: String,
    val title: String,
    val status: String,
)

fun TaskSummaryResponse.toModel(): SignalTaskModel = SignalTaskModel(
    id = id,
    title = title,
    status = status,
)
