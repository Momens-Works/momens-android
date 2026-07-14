package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalEvidenceSourceDto

enum class SignalEvidenceSourceModel {
    SLACK,
    GITHUB,
    FIGMA,
    FILE,
}

fun SignalEvidenceSourceDto.toModel(): SignalEvidenceSourceModel = when (this) {
    SignalEvidenceSourceDto.SLACK -> SignalEvidenceSourceModel.SLACK
    SignalEvidenceSourceDto.GITHUB -> SignalEvidenceSourceModel.GITHUB
    SignalEvidenceSourceDto.FIGMA -> SignalEvidenceSourceModel.FIGMA
    SignalEvidenceSourceDto.FILE -> SignalEvidenceSourceModel.FILE
}
