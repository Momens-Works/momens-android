package com.momens.android.data.signin.local.datasource

interface GoogleCredentialLocalDataSource {
    suspend fun clearCredentialState(): Result<Unit>
}
