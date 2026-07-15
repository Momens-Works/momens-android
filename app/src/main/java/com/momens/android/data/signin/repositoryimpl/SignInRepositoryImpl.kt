package com.momens.android.data.signin.repositoryimpl

import com.momens.android.core.local.ProjectManager
import com.momens.android.core.local.TokenManager
import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.signin.local.datasource.DeviceLocalDataSource
import com.momens.android.data.signin.local.datasource.GoogleCredentialLocalDataSource
import com.momens.android.data.signin.remote.datasource.SignInRemoteDataSource
import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val googleCredentialLocalDataSource: GoogleCredentialLocalDataSource,
    private val signInRemoteDataSource: SignInRemoteDataSource,
    private val deviceLocalDataSource: DeviceLocalDataSource,
    private val tokenManager: TokenManager,
    private val projectManager: ProjectManager,
) : SignInRepository {

    override suspend fun signInWithGoogle(
        idToken: String,
    ): Result<Unit> = suspendRunCatching {
        val response = signInRemoteDataSource.postGoogleToken(
            request = SignInTokenRequest(
                idToken = idToken,
                device = deviceLocalDataSource.getDeviceModel(),
            ),
        )

        tokenManager.saveTokens(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
        )

        val bootstrapResponse = signInRemoteDataSource.getMobileBootstrap()
        projectManager.saveProjectContext(
            projectId = bootstrapResponse.defaultProjectId,
            avatarUrl = bootstrapResponse.me.user.avatarUrl,
        )
    }

    override suspend fun signOut(): Result<Unit> = suspendRunCatching {
        googleCredentialLocalDataSource.clearCredentialState().getOrThrow()
        tokenManager.clearTokens()
        projectManager.clearProjectContext()
    }
}
