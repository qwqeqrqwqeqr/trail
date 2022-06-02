package kr.ac.kgu.app.trail.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kr.ac.kgu.app.trail.data.datasource.local.dao.CourseDao
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.service.kakao.KakaoService
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.data.service.trail.TrailService
import kr.ac.kgu.app.trail.repository.*


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideAuthRepository (
        authService : AuthService,
        userInfoDao: UserInfoDao,
        appDataStore: AppDataStore
    ): AuthRepository = AuthRepositoryImpl(authService,userInfoDao,appDataStore)


    @ViewModelScoped
    @Provides
    fun provideCourseRepository(
        trailService : TrailService,
        appDataStore: AppDataStore,
        courseDao: CourseDao):CourseRepository =  CourseRepositoryImpl(trailService, appDataStore,courseDao)


    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        trailService : TrailService):UserRepository =  UserRepositoryImpl(trailService)


    @ViewModelScoped
    @Provides
    fun provideHistoryRepository(
        trailService : TrailService):HistoryRepository =  HistoryRepositoryImpl(trailService)


    @ViewModelScoped
    @Provides
    fun providekakaoRepository(
        kakaoService: KakaoService,
        userInfoDao: UserInfoDao,
    ):KaKaoRepository =  KakaoRepositoryImpl(kakaoService,userInfoDao)

}