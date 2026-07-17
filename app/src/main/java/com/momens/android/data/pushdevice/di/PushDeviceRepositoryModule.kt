package com.momens.android.data.pushdevice.di

import com.momens.android.data.pushdevice.repository.PushDeviceRepository
import com.momens.android.data.pushdevice.repositoryimpl.PushDeviceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PushDeviceRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPushDeviceRepository(
        pushDeviceRepositoryImpl: PushDeviceRepositoryImpl,
    ): PushDeviceRepository
}
