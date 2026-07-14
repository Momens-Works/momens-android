package com.momens.android.presentation.signal.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.common.extension.toRelativeTimeText
import com.momens.android.data.signal.model.SignalEvidenceModel
import com.momens.android.data.signal.model.SignalEvidenceSourceModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class SignalEvidenceUiModel(
    val sourceRefId: String,
    val source: SignalAccordionType,
    val time: String,
    val target: String,
    val change: String,
    val impact: String,
    val sourceUrl: String? = null,
)

fun List<SignalEvidenceModel>.toUiModels(): ImmutableList<SignalEvidenceUiModel> =
    map { it.toUiModel() }.toImmutableList()

fun SignalEvidenceModel.toUiModel(): SignalEvidenceUiModel = SignalEvidenceUiModel(
    sourceRefId = sourceRefId,
    source = source.toUiType(),
    time = occurredAt.toRelativeTimeText(),
    target = details.target,
    change = details.change,
    impact = details.impact,
    sourceUrl = sourceUrl,
)

private fun SignalEvidenceSourceModel.toUiType(): SignalAccordionType = when (this) {
    SignalEvidenceSourceModel.SLACK -> SignalAccordionType.SLACK
    SignalEvidenceSourceModel.GITHUB -> SignalAccordionType.GITHUB
    SignalEvidenceSourceModel.FIGMA -> SignalAccordionType.FIGMA
    SignalEvidenceSourceModel.FILE -> SignalAccordionType.FILE
}
