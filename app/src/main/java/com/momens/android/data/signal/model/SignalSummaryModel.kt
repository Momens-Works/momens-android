package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalSummaryDto

data class SignalSummaryModel(
    val id: String,
    val type: SignalTypeModel,
    val title: String,
    val impact: String?,
    val minsuSuggestion: String?,
)

fun SignalSummaryDto.toModel(): SignalSummaryModel = SignalSummaryModel(
    id = id,
    type = type.toModel(),
    title = title,
    impact = impact,
    minsuSuggestion = minsuSuggestion,
)
