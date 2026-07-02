package com.momens.android.core.common.extension

import android.R.id.message
import timber.log.Timber


suspend inline fun <T> Result<T>.onLogFailure(
    message: String? = null,
    crossinline action: suspend (exception: Throwable) -> Unit
): Result<T> = onFailure { e ->
    Timber.e(e, message)
    action(e)
}
