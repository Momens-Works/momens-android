package com.momens.android.core.designsystem.component.type

import androidx.annotation.DrawableRes
import com.momens.android.R
import kotlinx.serialization.Serializable

@Serializable
enum class MomensStatusEditType(
    val key: String,
    val label: String,
    @param:DrawableRes val iconRes: Int,
) {
    BACKLOG(
        key = "backlog",
        label = "백로그",
        iconRes = R.drawable.ic_backlog,
    ),
    TODO(
        key = "todo",
        label = "투두",
        iconRes = R.drawable.ic_todo,
    ),
    IN_PROGRESS(
        key = "in_progress",
        label = "진행중",
        iconRes = R.drawable.ic_inprogress,
    ),
    DONE(
        key = "done",
        label = "완료",
        iconRes = R.drawable.ic_done,
    ),
    CANCELED(
        key = "canceled",
        label = "취소",
        iconRes = R.drawable.ic_canceled,
    ),
}
