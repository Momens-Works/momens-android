package com.momens.android.presentation.project.taskdetail.model

import androidx.compose.runtime.Immutable
import com.momens.android.core.common.extension.toKstDateTimeText
import com.momens.android.data.project.taskdetail.model.TaskMaterialKindModel
import com.momens.android.data.project.taskdetail.model.TaskMaterialModel
import com.momens.android.presentation.signal.model.SignalAccordionType

@Immutable
data class TaskDetailFileModel(
    val id: String,
    val title: String,
    val summary: String,
    val kind: SignalAccordionType,
    val sourceUrl: String?,
    val createdAtText: String,
)

fun TaskMaterialModel.toUiModel(): TaskDetailFileModel = TaskDetailFileModel(
    id = id,
    title = title.orEmpty(),
    summary = summary.orEmpty(),
    kind = kind.toUiType(),
    sourceUrl = sourceUrl,
    createdAtText = createdAt.toKstDateTimeText(),
)

private fun TaskMaterialKindModel.toUiType(): SignalAccordionType = when (this) {
    TaskMaterialKindModel.SLACK -> SignalAccordionType.SLACK
    TaskMaterialKindModel.GITHUB -> SignalAccordionType.GITHUB
    TaskMaterialKindModel.FIGMA -> SignalAccordionType.FIGMA
    TaskMaterialKindModel.FILE -> SignalAccordionType.FILE
}
