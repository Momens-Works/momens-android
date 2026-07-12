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
import com.momens.android.data.signin.model.GoogleUserProfile
import com.momens.android.data.signin.remote.datasource.GoogleAuthDataSource
import com.momens.android.data.signin.remote.datasource.GoogleLoginCancelledException
import com.momens.android.data.signin.remote.datasource.GoogleLoginFailedException
import javax.inject.Inject
import javax.inject.Named

class CredentialManagerGoogleAuthDataSourceImpl @Inject constructor(
    private val credentialManager: CredentialManager,
    @Named("google_server_client_id")
    private val googleWebClientId: String,
) : GoogleAuthDataSource {

    override suspend fun signIn(
        context: Context,
    ): Result<GoogleUserProfile> {
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

                GoogleUserProfile(
                    idToken = googleCredential.idToken,
                    email = googleCredential.email,
                    displayName = googleCredential.displayName,
                    profileImageUrl = googleCredential.profilePictureUri?.toString(),
                )
            } else {
                throw GoogleLoginFailedException(
                    message = "Invalid Google credential type.",
                )
            }
        }.recoverCatching { throwable ->
            when (throwable) {
                is GetCredentialCancellationException -> {
                    throw GoogleLoginCancelledException()
                }

                is GetCredentialException -> {
                    throw GoogleLoginFailedException(
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
