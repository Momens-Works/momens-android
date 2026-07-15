package com.momens.android.presentation.signal.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.SignalTagType
import com.momens.android.data.signal.model.SignalDetailModel
import com.momens.android.data.signal.model.SignalSummaryModel
import com.momens.android.data.signal.model.SignalTypeModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class SignalCardUiModel(
    val id: String,
    val type: SignalTagType,
    val title: String,
    val impact: String,
    val minsuSuggestion: String,
)

fun List<SignalSummaryModel>.toUiModels(): ImmutableList<SignalCardUiModel> =
    map { it.toUiModel() }.toImmutableList()

fun SignalSummaryModel.toUiModel(): SignalCardUiModel = SignalCardUiModel(
    id = id,
    type = type.toUiType(),
    title = title,
    impact = impact ?: "",
    minsuSuggestion = minsuSuggestion ?: "",
)

fun SignalDetailModel.toUiModel(): SignalCardUiModel = SignalCardUiModel(
    id = id,
    type = type.toUiType(),
    title = title,
    impact = impact ?: "",
    minsuSuggestion = minsuSuggestion ?: "",
)

private fun SignalTypeModel.toUiType(): SignalTagType = when (this) {
    SignalTypeModel.RISK -> SignalTagType.RISK
    SignalTypeModel.DECISION -> SignalTagType.DECISION
    SignalTypeModel.CHANGE -> SignalTagType.CHANGE
    SignalTypeModel.QUESTION -> SignalTagType.QUESTION
}
