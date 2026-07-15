package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalDetailResponse

data class SignalDetailModel(
    val id: String,
    val type: SignalTypeModel,
    val title: String,
    val impact: String?,
    val evidence: List<SignalEvidenceModel>,
    val minsuSuggestion: String?,
)

fun SignalDetailResponse.toModel(): SignalDetailModel = SignalDetailModel(
    id = id,
    type = type.toModel(),
    title = title,
    impact = impact,
    evidence = evidence.map { it.toModel() },
    minsuSuggestion = minsuSuggestion,
)
