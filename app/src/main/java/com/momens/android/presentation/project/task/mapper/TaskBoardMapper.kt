package com.momens.android.presentation.project.task.mapper

import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.ImportantTone
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.data.project.task.model.TaskBoard
import com.momens.android.data.project.task.model.TaskGroup
import com.momens.android.data.project.task.model.TaskItem
import com.momens.android.presentation.project.task.TaskUiState
import com.momens.android.presentation.project.task.model.MomensTaskButtonType
import com.momens.android.presentation.project.task.model.TaskItemData
import com.momens.android.presentation.project.task.model.TaskSectionUiModel
import kotlinx.collections.immutable.toPersistentList

/**
 * data 레이어의 [TaskBoard]를 화면 표시용 [TaskUiState]로 변환합니다.
 *
 * 서버가 문자열로 내려주는 role/priority/groupKey를 presentation enum으로 해석하고,
 * 서버 응답에 없는 [TaskItemData.tone]은 기본값(WHITE)으로 채웁니다.
 */
fun TaskBoard.toUiState(): TaskUiState = TaskUiState(
    title = title,
    description = description,
    sections = groups.mapNotNull { it.toSectionOrNull() }.toPersistentList(),
)

private fun TaskGroup.toSectionOrNull(): TaskSectionUiModel? {
    val type = groupKey.toStatusTypeOrNull() ?: return null
    return TaskSectionUiModel(
        type = type,
        tasks = tasks.map { it.toItemData() }.toPersistentList(),
    )
}

/**
 * data 레이어의 [TaskItem]을 화면 아이템 [TaskItemData]로 변환합니다.
 * 태스크 조회/생성 양쪽에서 공통으로 사용합니다.
 */
fun TaskItem.toItemData(): TaskItemData = TaskItemData(
    id = id,
    title = title,
    role = role.toButtonType(),
    priority = priority.toImportantLevel(),
    materialCount = materialCount,
    tone = ImportantTone.WHITE,
)

/** presentation enum을 서버 요청 문자열(소문자)로 변환합니다. */
fun MomensTaskButtonType.toRequestValue(): String = name.lowercase()

fun ImportantLevel.toRequestValue(): String = name.lowercase()

private fun String.toStatusTypeOrNull(): MomensStatusEditType? =
    MomensStatusEditType.entries.firstOrNull { it.key == this }

private fun String.toButtonType(): MomensTaskButtonType =
    MomensTaskButtonType.entries.firstOrNull { it.name.equals(this, ignoreCase = true) }
        ?: MomensTaskButtonType.PM

private fun String.toImportantLevel(): ImportantLevel =
    ImportantLevel.entries.firstOrNull { it.name.equals(this, ignoreCase = true) }
        ?: ImportantLevel.LOW
