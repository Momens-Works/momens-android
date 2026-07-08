package com.momens.android.core.designsystem.component.type

import androidx.annotation.DrawableRes
import com.momens.android.R

enum class MomensStatusEditType(
    val label: String,
    @param:DrawableRes val iconRes: Int,
) {
    BACKLOG(
        label = "백로그",
        iconRes = R.drawable.ic_backlog,
    ),
    TODO(
        label = "투두",
        iconRes = R.drawable.ic_todo,
    ),
    INPROGRESS(
        label = "진행중",
        iconRes = R.drawable.ic_inprogress,
    ),
    DONE(
        label = "완료",
        iconRes = R.drawable.ic_done,
    ),
    CANCELED(
        label = "취소",
        iconRes = R.drawable.ic_canceled,
    ),
}
