package com.momens.android.data.signal.model

import com.momens.android.data.signal.remote.dto.response.SignalListResponse

data class SignalListModel(
    val title: String,
    val description: String,
    val signals: List<SignalSummaryModel>,
)

fun SignalListResponse.toModel(): SignalListModel = SignalListModel(
    title = title,
    description = description,
    signals = signals.map { it.toModel() },
)
