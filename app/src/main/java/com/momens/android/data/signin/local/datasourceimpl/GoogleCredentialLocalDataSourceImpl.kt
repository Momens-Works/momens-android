package com.momens.android.data.signin.local.datasourceimpl

import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.momens.android.data.signin.local.datasource.GoogleCredentialLocalDataSource
import javax.inject.Inject

class GoogleCredentialLocalDataSourceImpl @Inject constructor(
    private val credentialManager: CredentialManager,
) : GoogleCredentialLocalDataSource {

    override suspend fun clearCredentialState(): Result<Unit> = runCatching {
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest(),
        )
    }
}
