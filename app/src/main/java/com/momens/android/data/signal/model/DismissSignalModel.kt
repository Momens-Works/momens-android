package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.DismissResponse

data class DismissSignalModel(
    val signal: SignalActionResultModel,
)

fun DismissResponse.toModel(): DismissSignalModel = DismissSignalModel(
    signal = signal.toModel(),
)
