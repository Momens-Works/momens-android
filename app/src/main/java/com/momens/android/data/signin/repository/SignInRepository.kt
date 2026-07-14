package com.momens.android.data.signin.repository

interface SignInRepository {
    suspend fun signInWithGoogle(
        idToken: String,
    ): Result<Unit>

    suspend fun signOut(): Result<Unit>
}
