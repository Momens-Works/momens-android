package com.momens.android.presentation.project.taskedit.component.mapper

import com.momens.android.data.project.taskedit.remote.dto.request.ChecklistItems
import com.momens.android.data.project.taskedit.remote.dto.request.TaskEditRequestDto
import com.momens.android.data.project.taskedit.remote.dto.response.TaskEditMembersResponseDto
import com.momens.android.presentation.project.model.Assignee
import com.momens.android.presentation.project.model.ChecklistItem
import com.momens.android.presentation.project.taskedit.model.ChecklistItemState
import com.momens.android.presentation.project.taskedit.model.Task
import com.momens.android.presentation.project.taskedit.model.TaskEditPayload
import com.momens.android.presentation.project.taskedit.navigation.TaskEdit
import kotlinx.collections.immutable.toPersistentList
import java.util.UUID

fun TaskEditMembersResponseDto.toModel(): List<Assignee> =
    members.map { member ->
        Assignee(
            id = member.id,
            name = member.name,
            url = member.avatarUrl,
        )
    }

fun TaskEdit.toTask(payload: TaskEditPayload): Task = Task(
    taskId = taskId,
    titleState = title,
    status = status,
    role = role,
    assignee = payload.assignee,
    priority = priority,
    purposeState = purpose.orEmpty(),
    checklist = payload.checklist.map { it.toChecklistItemState() }.toPersistentList(),
)

private fun ChecklistItem.toChecklistItemState(): ChecklistItemState = ChecklistItemState(
    id = id,
    localId = UUID.randomUUID().toString(),
    title = title,
    completed = completed,
)

fun Task.toRequestDto(): TaskEditRequestDto = TaskEditRequestDto(
    title = titleState,
    role = role.name,
    assigneeId = assignee?.id,
    priority = priority.name,
    status = status.name,
    purpose = purposeState,
    checklistItems = checklist.map {
        ChecklistItems(id = it.id, title = it.title, completed = it.completed)
    },
)
