package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalActionResultDto

data class SignalActionResultModel(
    val id: String,
    val action: String,
)

fun SignalActionResultDto.toModel(): SignalActionResultModel = SignalActionResultModel(
    id = id,
    action = action,
)
