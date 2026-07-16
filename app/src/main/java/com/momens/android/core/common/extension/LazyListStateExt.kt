package com.momens.android.core.common.extension

import androidx.compose.foundation.lazy.LazyListItemInfo

val LazyListItemInfo.offsetEnd: Int
    get() = this.offset + this.size
