package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalEvidenceSourceResponse

enum class SignalEvidenceSourceModel {
    SLACK,
    GITHUB,
    FIGMA,
    FILE,
}

fun SignalEvidenceSourceResponse.toModel(): SignalEvidenceSourceModel = when (this) {
    SignalEvidenceSourceResponse.SLACK -> SignalEvidenceSourceModel.SLACK
    SignalEvidenceSourceResponse.GITHUB -> SignalEvidenceSourceModel.GITHUB
    SignalEvidenceSourceResponse.FIGMA -> SignalEvidenceSourceModel.FIGMA
    SignalEvidenceSourceResponse.FILE -> SignalEvidenceSourceModel.FILE
}
