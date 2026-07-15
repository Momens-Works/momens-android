package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalActionResultResponse

data class SignalActionResultModel(
    val id: String,
    val action: String,
)

fun SignalActionResultResponse.toModel(): SignalActionResultModel = SignalActionResultModel(
    id = id,
    action = action,
)
