package com.momens.android.data.signin.remote.datasource

class SignInCancelledException : Exception("Google login cancelled")

class SignInFailedException(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message, cause)
