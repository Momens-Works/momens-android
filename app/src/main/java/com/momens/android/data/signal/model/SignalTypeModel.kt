package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalTypeDto

enum class SignalTypeModel {
    RISK,
    CHANGE,
    DECISION,
    QUESTION,
}

fun SignalTypeDto.toModel(): SignalTypeModel = when (this) {
    SignalTypeDto.RISK -> SignalTypeModel.RISK
    SignalTypeDto.CHANGE -> SignalTypeModel.CHANGE
    SignalTypeDto.DECISION -> SignalTypeModel.DECISION
    SignalTypeDto.QUESTION -> SignalTypeModel.QUESTION
}
