package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalTypeResponse

enum class SignalTypeModel {
    RISK,
    CHANGE,
    DECISION,
    QUESTION,
}

fun SignalTypeResponse.toModel(): SignalTypeModel = when (this) {
    SignalTypeResponse.RISK -> SignalTypeModel.RISK
    SignalTypeResponse.CHANGE -> SignalTypeModel.CHANGE
    SignalTypeResponse.DECISION -> SignalTypeModel.DECISION
    SignalTypeResponse.QUESTION -> SignalTypeModel.QUESTION
}
