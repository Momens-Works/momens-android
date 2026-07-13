package com.momens.android.data

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Retrofit Service 인터페이스를 Hilt에 등록하는 모듈입니다.
 *
 * 새로운 서버 API를 연결할 때 `remote/service`에 Service 인터페이스를 만들고, 이 모듈에 provider를 추가합니다.
 *
 * 예시 - Service 등록
 * ```
 * @Provides
 * @Singleton
 * fun provideProjectService(
 *     retrofit: Retrofit,
 * ): ProjectService = retrofit.create(ProjectService::class.java)
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule{

}
