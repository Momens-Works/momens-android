package com.momens.android.data.signin.repositoryimpl

import com.momens.android.core.local.project.ProjectManager
import com.momens.android.core.local.TokenManager
import com.momens.android.core.local.model.ProjectContextModel
import com.momens.android.core.util.suspendRunCatching
import com.momens.android.data.signin.local.datasource.DeviceLocalDataSource
import com.momens.android.data.signin.local.datasource.GoogleCredentialLocalDataSource
import com.momens.android.data.signin.remote.datasource.SignInRemoteDataSource
import com.momens.android.data.signin.remote.dto.request.SignInTokenRequest
import com.momens.android.data.signin.remote.dto.request.TokenRefreshRequest
import com.momens.android.data.signin.repository.SignInRepository
import javax.inject.Inject
import retrofit2.HttpException

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

        runCatching {
            syncProjectContext()
        }.onFailure {
            tokenManager.clearTokens()
            projectManager.clearProjectContext()
            throw it
        }
    }

    override suspend fun refreshSession(): Result<Unit> {
        val refreshToken = tokenManager.getRefreshToken()

        if (refreshToken.isNullOrBlank()) {
            tokenManager.clearTokens()
            return Result.failure(MissingRefreshTokenException())
        }

        val result = suspendRunCatching {
            val response = signInRemoteDataSource.refreshToken(
                request = TokenRefreshRequest(refreshToken = refreshToken),
            )

            tokenManager.saveTokens(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
            )

            // 예전 세션 등으로 projectId가 비어있을 수 있어, 토큰 갱신 시점에도 채워지도록 보장합니다.
            if (projectManager.currentProjectContext.projectId == null) {
                syncProjectContext()
            }
        }

        val failure = result.exceptionOrNull()
        if (failure is HttpException && failure.code() == HTTP_UNAUTHORIZED) {
            tokenManager.clearTokens()
        }

        return result
    }

    private suspend fun syncProjectContext() {
        val bootstrapResponse = signInRemoteDataSource.getMobileBootstrap()
        projectManager.saveProjectContext(
            projectContext = ProjectContextModel(
                projectId = bootstrapResponse.defaultProjectId,
                avatarUrl = bootstrapResponse.me.avatarUrl,
            ),
        )
    }

    override suspend fun signOut(): Result<Unit> = suspendRunCatching {
        googleCredentialLocalDataSource.clearCredentialState().getOrThrow()
        tokenManager.clearTokens()
        projectManager.clearProjectContext()
    }

    private companion object {
        private const val HTTP_UNAUTHORIZED = 401
    }
}

private class MissingRefreshTokenException : IllegalStateException(
    "저장된 refresh token이 없습니다.",
)
