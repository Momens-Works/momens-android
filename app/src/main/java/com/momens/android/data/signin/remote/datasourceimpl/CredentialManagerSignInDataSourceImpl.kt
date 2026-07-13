package com.momens.android.data.signin.remote.datasourceimpl

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.momens.android.data.signin.model.SignInUserProfile
import com.momens.android.data.signin.remote.datasource.SignInCancelledException
import com.momens.android.data.signin.remote.datasource.SignInDataSource
import com.momens.android.data.signin.remote.datasource.SignInFailedException
import javax.inject.Inject
import javax.inject.Named

class CredentialManagerSignInDataSourceImpl @Inject constructor(
    private val credentialManager: CredentialManager,
    @Named("google_server_client_id")
    private val googleWebClientId: String,
) : SignInDataSource {

    override suspend fun signIn(
        context: Context,
    ): Result<SignInUserProfile> {
        return runCatching {
            require(googleWebClientId.isNotBlank()) {
                "google_server_client_id is blank."
            }

            val googleIdOption = GetSignInWithGoogleOption.Builder(
                googleWebClientId,
            ).build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val response = credentialManager.getCredential(
                context = context,
                request = request,
            )

            val credential = response.credential

            if (
                credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val googleCredential = GoogleIdTokenCredential.createFrom(
                    credential.data,
                )

                SignInUserProfile(
                    idToken = googleCredential.idToken,
                    email = googleCredential.email,
                    displayName = googleCredential.displayName,
                    profileImageUrl = googleCredential.profilePictureUri?.toString(),
                )
            } else {
                throw SignInFailedException(
                    message = "Invalid Google credential type.",
                )
            }
        }.recoverCatching { throwable ->
            when (throwable) {
                is GetCredentialCancellationException -> {
                    throw SignInCancelledException()
                }

                is GetCredentialException -> {
                    throw SignInFailedException(
                        message = throwable.message ?: "Google login failed.",
                        cause = throwable,
                    )
                }

                else -> {
                    throw throwable
                }
            }
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return runCatching {
            credentialManager.clearCredentialState(
                ClearCredentialStateRequest(),
            )
        }
    }
}
