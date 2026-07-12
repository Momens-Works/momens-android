package com.momens.android.data.signin.remote.datasource

class GoogleLoginCancelledException : Exception("Google login cancelled")

class GoogleLoginFailedException(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message, cause)
