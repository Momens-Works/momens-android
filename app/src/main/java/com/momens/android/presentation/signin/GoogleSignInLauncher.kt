package com.momens.android.presentation.signin

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.credentials.CredentialManager
import com.momens.android.R
import com.momens.android.data.signin.remote.datasource.SignInDataSource
import com.momens.android.data.signin.remote.datasourceimpl.CredentialManagerSignInDataSourceImpl

internal class GoogleSignInLauncher(
    private val signInDataSource: SignInDataSource,
) {

    suspend fun launch(activityContext: Context): Result<String> =
        signInDataSource.signIn(activityContext).map { profile -> profile.idToken }
}

@Composable
internal fun rememberGoogleSignInLauncher(): GoogleSignInLauncher {
    val applicationContext = LocalContext.current.applicationContext
    val googleServerClientId = stringResource(R.string.google_server_client_id)

    return remember(applicationContext, googleServerClientId) {
        GoogleSignInLauncher(
            signInDataSource = CredentialManagerSignInDataSourceImpl(
                credentialManager = CredentialManager.create(applicationContext),
                googleWebClientId = googleServerClientId,
            ),
        )
    }
}
