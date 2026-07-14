package com.momens.android.data.signin.di

import com.momens.android.data.signin.repository.SignInRepository
import com.momens.android.data.signin.repositoryimpl.SignInRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository 인터페이스와 구현체를 Hilt에 연결하는 모듈입니다.
 *
 * 새로운 기능을 붙일 때 `repository`에 인터페이스를 만들고, `repositoryimpl`에 구현체를 만든 뒤
 * 이 모듈에 바인딩을 추가합니다.
 *
 * 예시 - Repository 바인딩
 * ```
 * @Binds
 * @Singleton
 * abstract fun bindProjectRepository(
 *     projectRepositoryImpl: ProjectRepositoryImpl,
 * ): ProjectRepository
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SignInRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSignInRepository(
        signInRepositoryImpl: SignInRepositoryImpl,
    ): SignInRepository
}
