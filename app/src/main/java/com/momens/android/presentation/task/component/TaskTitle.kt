package com.momens.android.presentation.task.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun TaskTitle(){
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "프로젝트 태스크",
            color = MomensTheme.colors.black,
            style = MomensTheme.typography.titleB20
        )

        Text(
            text = "업무를 한눈에 확인하고 상세 내용을 확인하세요.",
            color = MomensTheme.colors.gray400,
            style = MomensTheme.typography.bodyM14
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskTitlePreview(){
    MomensTheme {
        TaskTitle()
    }
}
