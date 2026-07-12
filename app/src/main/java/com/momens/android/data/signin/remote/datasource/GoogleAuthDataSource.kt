package com.momens.android.data.signin.remote.datasource

import android.content.Context
import com.momens.android.data.signin.model.GoogleUserProfile

interface GoogleAuthDataSource {
    suspend fun signIn(context: Context): Result<GoogleUserProfile>

    suspend fun signOut(): Result<Unit>
}
