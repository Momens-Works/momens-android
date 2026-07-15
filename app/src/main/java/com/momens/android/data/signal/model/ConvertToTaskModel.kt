package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.ConvertToTaskResponse

data class ConvertToTaskModel(
    val task: SignalTaskModel,
    val signal: SignalActionResultModel,
)

fun ConvertToTaskResponse.toModel(): ConvertToTaskModel = ConvertToTaskModel(
    task = task.toModel(),
    signal = signal.toModel(),
)
