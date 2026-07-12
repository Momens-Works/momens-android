package com.momens.android.data.signin.remote.datasource

import android.content.Context
import com.momens.android.data.signin.model.SignInUserProfile

interface SignInDataSource {
    suspend fun signIn(context: Context): Result<SignInUserProfile>

    suspend fun signOut(): Result<Unit>
}
