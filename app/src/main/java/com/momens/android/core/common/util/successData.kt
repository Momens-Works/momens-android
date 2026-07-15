package com.momens.android.core.common.util

import com.momens.android.core.common.state.UiState

val <T> UiState<T>.successData: T?
    get() = (this as? UiState.Success)?.data
