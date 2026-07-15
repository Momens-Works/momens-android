package com.momens.android.core.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.momens.android.core.local.ProjectManager
import com.momens.android.core.local.ProjectManagerImpl
import com.momens.android.core.local.TokenManager
import com.momens.android.core.local.TokenManagerImpl
import com.momens.android.core.local.constant.DataStoreConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.userInfoDataStore by preferencesDataStore(
    name = DataStoreConstant.MOMENS_USER_INFO_PREFS
)

/**
 * Preferences DataStore와 TokenManager를 Hilt 그래프에 등록하는 모듈입니다.
 *
 * 예시 - Repository에서 TokenManager 주입
 * ```
 * class AuthRepository @Inject constructor(
 *     private val tokenManager: TokenManager,
 * )
 * ```
 *
 * 예시 - 새로운 로컬 저장소가 필요할 때
 * ```
 * @Provides
 * @Singleton
 * fun provideFooLocalDataSource(
 *     dataStore: DataStore<Preferences>,
 * ): FooLocalDataSource = FooLocalDataSource(dataStore)
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserInfoDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.userInfoDataStore

    @Provides
    @Singleton
    fun provideTokenManager(
        dataStore: DataStore<Preferences>
    ): TokenManager = TokenManagerImpl(dataStore)

    @Provides
    @Singleton
    fun provideProjectManager(
        dataStore: DataStore<Preferences>,
    ): ProjectManager = ProjectManagerImpl(dataStore)
}
