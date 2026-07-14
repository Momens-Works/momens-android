package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalEvidenceDto

data class SignalEvidenceModel(
    val sourceRefId: String,
    val source: SignalEvidenceSourceModel,
    val occurredAt: String?,
    val details: SignalEvidenceDetailsModel,
    val sourceUrl: String?,
)

fun SignalEvidenceDto.toModel(): SignalEvidenceModel = SignalEvidenceModel(
    sourceRefId = sourceRefId,
    source = source.toModel(),
    occurredAt = occurredAt,
    details = details.toModel(),
    sourceUrl = sourceUrl,
)
