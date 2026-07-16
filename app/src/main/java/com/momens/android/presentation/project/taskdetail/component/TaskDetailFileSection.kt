package com.momens.android.presentation.project.taskdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.list.MomensFileListItem
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskdetail.model.TaskDetailFileModel
import com.momens.android.presentation.signal.model.SignalAccordionType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TaskDetailFileSection(
    files: ImmutableList<TaskDetailFileModel>,
    onFileClick: (TaskDetailFileModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "관련자료",
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.gray900,
        )

        if (files.isEmpty()) {
            Text(
                text = "관련자료가 등록되지 않았습니다.",
                style = MomensTheme.typography.bodyM12,
                color = MomensTheme.colors.gray300,
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                files.forEach { file ->
                    key(file.id) {
                        MomensFileListItem(
                            title = file.title,
                            role = file.kind.text,
                            category = file.createdAtText,
                            iconResId = file.kind.icon,
                            onClick = { onFileClick(file) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskDetailFileSectionPreview() {
    MomensTheme {
        TaskDetailFileSection(
            modifier = Modifier.padding(16.dp),
            files = persistentListOf(
                TaskDetailFileModel(
                    id = "1",
                    title = "회원가입 에러 메시지 정책 초안",
                    summary = "회원가입의 MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    kind = SignalAccordionType.FIGMA,
                    sourceUrl = "https://example.com/docs/1",
                    createdAtText = "2026.07.15 22:48",
                ),
                TaskDetailFileModel(
                    id = "2",
                    title = "회원가입 에러 메시지 정책 초안",
                    summary = "회원가입의 MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    kind = SignalAccordionType.FILE,
                    sourceUrl = "https://example.com/docs/2",
                    createdAtText = "2026.07.15 22:50",
                ),
            ),
            onFileClick = {},
        )
    }
}

@Preview(showBackground = true, name = "빈 상태")
@Composable
private fun TaskDetailFileSectionEmptyPreview() {
    MomensTheme {
        TaskDetailFileSection(
            modifier = Modifier.padding(16.dp),
            files = persistentListOf(),
            onFileClick = {},
        )
    }
}
