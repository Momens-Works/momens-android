package com.momens.android.core.network

import com.momens.android.BuildConfig
import com.momens.android.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber

/**
 * 앱 전체에서 공통으로 사용할 네트워크 의존성을 Hilt에 등록하는 모듈입니다.
 *
 * - Json: 서버 응답을 kotlinx serialization으로 파싱합니다.
 * - OkHttpClient: 로깅, API version, 인증 헤더, 401 처리를 담당합니다.
 * - Retrofit: 각 API Service 인터페이스를 생성하는 진입점입니다.
 *
 * 예시 - API Service 추가
 * ```
 * interface AuthService {
 *     @POST("/api/auth/google/token")
 *     suspend fun signInWithGoogle(
 *         @Body request: GoogleTokenRequest,
 *     ): GoogleTokenResponse
 * }
 *
 * @Provides
 * @Singleton
 * fun provideAuthService(retrofit: Retrofit): AuthService =
 *     retrofit.create(AuthService::class.java)
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONTENT_TYPE = "application/json"
    private const val LOGGING_TAG = "okhttp"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    @Provides
    @Singleton
    fun provideJsonConverter(json: Json): Converter.Factory =
        json.asConverterFactory(CONTENT_TYPE.toMediaType())

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor { message ->
        when {
            message.isJsonObject() ->
                Timber.tag(LOGGING_TAG).d(JSONObject(message).toString(4))

            message.isJsonArray() ->
                Timber.tag(LOGGING_TAG).d(JSONArray(message).toString(4))

            else ->
                Timber.tag(LOGGING_TAG).d("연결 정보 -> $message")
        }
    }.apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideDefaultOkHttpClient(
        loggingInterceptor: Interceptor,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .authenticator(tokenAuthenticator)
        .build()

    @Provides
    @Singleton
    fun provideDefaultRetrofit(
        client: OkHttpClient,
        factory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()
}
