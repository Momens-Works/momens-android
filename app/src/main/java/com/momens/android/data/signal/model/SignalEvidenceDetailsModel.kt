package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalEvidenceDetailsResponse

data class SignalEvidenceDetailsModel(
    val target: String?,
    val change: String?,
    val impact: String?,
)

fun SignalEvidenceDetailsResponse.toModel(): SignalEvidenceDetailsModel = SignalEvidenceDetailsModel(
    target = target,
    change = change,
    impact = impact,
)
