package com.momens.android.core.auth.google

interface GoogleSignInLauncher {
    suspend fun launch(): Result<String>
}
