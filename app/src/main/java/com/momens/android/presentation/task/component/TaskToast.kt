package com.momens.android.presentation.task.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.momens.android.core.designsystem.component.toast.MomensToast
import com.momens.android.core.designsystem.component.type.MomensToastType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun TaskToast(
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,

    ){
    MomensToast(
        modifier = modifier,
        title = "태스크가 등록되었습니다",
        description = "'투두' 에 추가됨",
        type = MomensToastType.BUTTON,
        onActionClick = onActionClick,
    )
}

@Preview(showBackground = true)
@Composable
fun TaskToastPreview(){
    MomensTheme {
        TaskToast(
            onActionClick = {}
        )
    }
}

