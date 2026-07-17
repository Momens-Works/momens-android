package com.momens.android.data.pushdevice.di

import com.momens.android.data.pushdevice.remote.datasource.PushDeviceRemoteDataSource
import com.momens.android.data.pushdevice.remote.datasourceimpl.PushDeviceRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PushDeviceDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindPushDeviceRemoteDataSource(
        pushDeviceRemoteDataSourceImpl: PushDeviceRemoteDataSourceImpl,
    ): PushDeviceRemoteDataSource
}
