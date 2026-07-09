package com.momens.android.core.designsystem.component.type

import androidx.annotation.DrawableRes
import com.momens.android.R

enum class MomensToastType(
    @param:DrawableRes val iconRes: Int
) {
    DEFAULT(R.drawable.ic_warning),
    BUTTON(R.drawable.ic_checkbox_fill)
}
