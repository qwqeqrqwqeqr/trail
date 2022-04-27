package kr.ac.kgu.app.trail.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.ac.kgu.app.trail.data.datastore.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.service.kakao.KakaoUserService
import kr.ac.kgu.app.trail.data.service.kakao.KakaoUserServiceImpl
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOKHttpClient(
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }


    @Singleton
    @Provides
    suspend fun provideAuthInterceptor(appDataStore: AppDataStore): Interceptor {
        val token = appDataStore.getAccessToken()
        return Interceptor { chain: Interceptor.Chain ->
            with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("Authorization",Constants.BEARER + token)
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
    fun provideKakaoUserService(@ApplicationContext context: Context
    ): KakaoUserService =
        KakaoUserServiceImpl(context)

}