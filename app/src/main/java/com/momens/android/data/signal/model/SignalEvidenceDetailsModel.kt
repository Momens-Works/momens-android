package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalEvidenceDetailsDto

data class SignalEvidenceDetailsModel(
    val target: String,
    val change: String,
    val impact: String,
)

fun SignalEvidenceDetailsDto.toModel(): SignalEvidenceDetailsModel = SignalEvidenceDetailsModel(
    target = target,
    change = change,
    impact = impact,
)
