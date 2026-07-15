package com.momens.android.presentation.project.taskdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun TaskDetailPurposeSection(
    purpose: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "목적",
            style = MomensTheme.typography.bodyB14,
            color = MomensTheme.colors.gray900,
        )

        if (purpose.isNullOrBlank()) {
            Text(
                text = "목적이 입력되지 않았습니다.",
                style = MomensTheme.typography.bodyM12,
                color = MomensTheme.colors.gray300,
            )
        } else {
            Text(
                text = purpose,
                style = MomensTheme.typography.bodyM12,
                color = MomensTheme.colors.gray800,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskDetailPurposeSectionPreview() {
    MomensTheme {
        TaskDetailPurposeSection(
            modifier = Modifier.padding(16.dp),
            purpose = "어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구어쩌구저쩌구어쩌구저쩌구어쩌구어쩌구" +
                "저쩌구어쩌구저쩌구어쩌구어쩌구저쩌구어쩌구저쩌구어쩌구구",
        )
    }
}

@Preview(showBackground = true, name = "빈 상태")
@Composable
private fun TaskDetailPurposeSectionEmptyPreview() {
    MomensTheme {
        TaskDetailPurposeSection(
            modifier = Modifier.padding(16.dp),
            purpose = null,
        )
    }
}
