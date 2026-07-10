package com.momens.android.presentation.task.taskcomponent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.momens.android.core.designsystem.component.toast.MomensToast
import com.momens.android.core.designsystem.component.type.MomensToastType

@Composable
fun TaskToast(
    modifier: Modifier = Modifier,
    onActionClick: () -> Unit = {},
){
    MomensToast(
        title = "태스크가 등록되었습니다",
        modifier = modifier,
        description = "'투두' 에 추가됨",
        type = MomensToastType.BUTTON,
        onActionClick = onActionClick,
    )
}

