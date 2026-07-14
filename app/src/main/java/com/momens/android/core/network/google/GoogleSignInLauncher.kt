package com.momens.android.core.network.google

interface GoogleSignInLauncher {
    suspend fun launch(): Result<String>
}
