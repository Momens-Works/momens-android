package com.momens.android.data.signin.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.momens.android.R
import com.momens.android.data.signin.remote.datasource.SignInDataSource
import com.momens.android.data.signin.remote.datasourceimpl.CredentialManagerSingnInDataSourceImpl
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
abstract class SignInDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindGoogleAuthDataSource(
        credentialManagerSingnInDataSourceImpl: CredentialManagerSingnInDataSourceImpl,
    ): SignInDataSource

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
