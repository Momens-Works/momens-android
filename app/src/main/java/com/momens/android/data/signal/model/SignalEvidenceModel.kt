package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalEvidenceResponse

data class SignalEvidenceModel(
    val sourceRefId: String,
    val source: SignalEvidenceSourceModel,
    val occurredAt: String?,
    val details: SignalEvidenceDetailsModel,
    val sourceUrl: String?,
)

fun SignalEvidenceResponse.toModel(): SignalEvidenceModel = SignalEvidenceModel(
    sourceRefId = sourceRefId,
    source = source.toModel(),
    occurredAt = occurredAt,
    details = details.toModel(),
    sourceUrl = sourceUrl,
)
