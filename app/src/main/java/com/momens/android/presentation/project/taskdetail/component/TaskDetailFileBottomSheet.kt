package com.momens.android.presentation.project.taskdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.bottomsheet.MomensBottomSheet
import com.momens.android.core.designsystem.component.dot.MomensDot
import com.momens.android.core.designsystem.component.textbox.MomensTextBox
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.project.taskdetail.model.TaskDetailFileModel
import com.momens.android.presentation.signal.model.SignalAccordionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailFileBottomSheet(
    file: TaskDetailFileModel,
    onDismiss: () -> Unit,
    onOpenSourceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MomensBottomSheet(
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = file.kind.text,
                    color = MomensTheme.colors.gray400,
                    style = MomensTheme.typography.captionM11,
                )

                MomensDot(color = MomensTheme.colors.gray400)

                Text(
                    text = file.createdAtText,
                    color = MomensTheme.colors.gray400,
                    style = MomensTheme.typography.captionM11,
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = file.title,
                color = MomensTheme.colors.black,
                style = MomensTheme.typography.bodyB16,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = file.summary,
                color = MomensTheme.colors.gray400,
                style = MomensTheme.typography.bodyM12,
            )

            Spacer(modifier = Modifier.height(20.dp))

            MomensTextBox(
                text = file.title,
                iconResId = file.kind.icon,
                iconColor = MomensTheme.colors.primary100,
                textColor = MomensTheme.colors.gray500,
                isArrowVisible = file.sourceUrl != null,
                onArrowClick = onOpenSourceClick,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskDetailFileBottomSheetPreview() {
    MomensTheme {
        TaskDetailFileBottomSheet(
            file = TaskDetailFileModel(
                id = "1",
                title = "회원가입 에러 메시지 정책 초안",
                summary = "회원가입의 MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                kind = SignalAccordionType.FIGMA,
                sourceUrl = "https://example.com",
                createdAtText = "2026.07.15 22:48",
            ),
            onDismiss = {},
            onOpenSourceClick = {},
        )
    }
}
