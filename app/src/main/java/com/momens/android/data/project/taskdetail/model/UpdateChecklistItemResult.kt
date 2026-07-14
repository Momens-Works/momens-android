package com.momens.android.data.project.taskdetail.model

import com.momens.android.data.project.taskdetail.remote.dto.response.UpdateChecklistItemResponse

data class UpdateChecklistItemResult(
    val completedCount: Int,
    val totalCount: Int,
    val item: TaskChecklistItemModel,
)

fun UpdateChecklistItemResponse.toModel(): UpdateChecklistItemResult = UpdateChecklistItemResult(
    completedCount = checklist.completedCount,
    totalCount = checklist.totalCount,
    item = checklist.item.toModel(),
)
