package com.momens.android.data.project.task.di

import com.momens.android.data.project.task.remote.datasource.TaskRemoteDataSource
import com.momens.android.data.project.task.remote.datasourceimpl.TaskRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DataSource 인터페이스와 구현체를 Hilt에 연결하는 모듈입니다.
 *
 * remote/local DataSource 레이어를 둘 때 `remote/datasource`에 인터페이스를 만들고,
 * `remote/datasourceimpl`에 구현체를 만든 뒤 이 모듈에 바인딩을 추가합니다.
 *
 * 예시 - RemoteDataSource 바인딩
 * ```
 * @Binds
 * @Singleton
 * abstract fun bindProjectRemoteDataSource(
 *     projectRemoteDataSourceImpl: ProjectRemoteDataSourceImpl,
 * ): ProjectRemoteDataSource
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TaskDataSourceModule{
    @Binds
    @Singleton
    abstract fun bindTaskRemoteDataSource(
        taskRemoteDataSourceImpl: TaskRemoteDataSourceImpl,
    ): TaskRemoteDataSource
}
