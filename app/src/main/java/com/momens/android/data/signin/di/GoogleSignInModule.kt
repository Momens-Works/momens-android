package com.momens.android.data.signin.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.momens.android.R
import com.momens.android.data.signin.remote.datasource.GoogleAuthDataSource
import com.momens.android.data.signin.remote.datasourceimpl.CredentialManagerGoogleAuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GoogleSignInModule {

    @Binds
    @Singleton
    abstract fun bindGoogleAuthDataSource(
        credentialManagerGoogleAuthDataSourceImpl: CredentialManagerGoogleAuthDataSourceImpl,
    ): GoogleAuthDataSource

    companion object {

        @Provides
        @Singleton
        fun provideCredentialManager(
            @ApplicationContext context: Context,
        ): CredentialManager {
            return CredentialManager.create(context)
        }

        @Provides
        @Singleton
        @Named("google_server_client_id")
        fun provideGoogleServerClientId(
            @ApplicationContext context: Context,
        ): String {
            return context.getString(R.string.google_server_client_id)
        }
    }
}
