package com.momens.android.data.signin.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.momens.android.R
import com.momens.android.data.signin.local.datasource.GoogleCredentialLocalDataSource
import com.momens.android.data.signin.local.datasourceimpl.GoogleCredentialLocalDataSourceImpl
import com.momens.android.data.signin.remote.datasource.DeviceLocalDataSource
import com.momens.android.data.signin.remote.datasource.SignInRemoteDataSource
import com.momens.android.data.signin.remote.datasourceimpl.DeviceLocalDataSourceImpl
import com.momens.android.data.signin.remote.datasourceimpl.SignInRemoteDataSourceImpl
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
    abstract fun bindGoogleCredentialLocalDataSource(
        googleCredentialLocalDataSourceImpl: GoogleCredentialLocalDataSourceImpl,
    ): GoogleCredentialLocalDataSource

    @Binds
    @Singleton
    abstract fun bindSignInRemoteDataSource(
        signInRemoteDataSourceImpl: SignInRemoteDataSourceImpl,
    ): SignInRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindDeviceLocalDataSource(
        deviceLocalDataSourceImpl: DeviceLocalDataSourceImpl,
    ): DeviceLocalDataSource

    companion object {

        @Provides
        @Singleton
        fun provideCredentialManager(
            @ApplicationContext context: Context,
        ): CredentialManager = CredentialManager.create(context)

        @Provides
        @Singleton
        @Named("google_server_client_id")
        fun provideGoogleServerClientId(
            @ApplicationContext context: Context,
        ): String = context.getString(R.string.google_server_client_id)
    }
}
