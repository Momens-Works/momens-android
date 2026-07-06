package com.momens.android.core.designsystem.type

import androidx.annotation.DrawableRes
import com.momens.android.R

enum class MomensTaskType(
    val label : String,
    @param: DrawableRes val iconRes: Int
){
    TODO(
        label = "투두",
        iconRes = R.drawable.ic_todo
    ),

    IN_PROGRESS(
        label = "진행중",
        iconRes = R.drawable.ic_inprogress
    ),

    DONE(
        label = "완료",
        iconRes = R.drawable.ic_done
    ),

    CANCELED(
        label = "취소",
        iconRes = R.drawable.ic_canceled
    ),

    BACKLOG(
        label = "백로그",
        iconRes = R.drawable.ic_backlog
    ),

}
