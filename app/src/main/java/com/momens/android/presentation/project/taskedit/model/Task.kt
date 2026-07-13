package com.momens.android.presentation.project.taskedit.model

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.ImportantLevel
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class Task(
    val taskId: String,
    val titleState: TextFieldState,
    val status: MomensStatusEditType,
    val role: TaskRole,
    val assignee: Assignee?,
    val priority: ImportantLevel,
    val purposeState: TextFieldState,
    val checklist: PersistentList<ChecklistItemState>,
){
    val checklistTotalCount: Int get() = checklist.size
    val checklistCompletedCount: Int get() = checklist.count { it.isChecked }

    companion object {
        val fake = Task(
            taskId = "1",
            titleState = TextFieldState("로그인 화면 구현"),
            status = MomensStatusEditType.TODO,
            role = TaskRole.BACKEND,
            assignee = Assignee("1", "김민지", null),
            priority = ImportantLevel.MEDIUM,
            purposeState = TextFieldState("사용자 인증 플로우 완성"),
            checklist = persistentListOf(
                ChecklistItemState(itemId = "1", title = TextFieldState(initialText = "로그인 API 연동 완료"), isChecked = true),
                ChecklistItemState(itemId = "2", title = TextFieldState(initialText = "에러 핸들링 처리"), isChecked = false),
            ),
        )
    }
}

@Immutable
data class Assignee(
    val id: String,
    val name: String,
    val url: String?,
)

enum class TaskRole(val label: String) {
    PM("PM"),
    DESIGN("Design"),
    FRONTEND("Frontend"),
    BACKEND("Backend"),
}
