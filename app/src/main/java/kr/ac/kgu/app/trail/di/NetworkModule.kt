package kr.ac.kgu.app.trail.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.model.UserToken
import kr.ac.kgu.app.trail.data.service.kakao.KakaoService
import kr.ac.kgu.app.trail.data.service.kakao.KakaoServiceImpl
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.data.service.trail.TrailService
import kr.ac.kgu.app.trail.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import javax.inject.Qualifier

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor,
        authInterceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build() }


    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC }


    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("Authorization", Constants.BEARER + UserToken.accessToken)
                    .build()
                proceed(newRequest)
            }
        }
    }


    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)


    @Provides
    @Singleton
    fun provideTrailService(retrofit: Retrofit): TrailService =
        retrofit.create(TrailService::class.java)

    @Provides
    @Singleton
    fun provideKakaoUserService(): KakaoService =  KakaoServiceImpl()

}
