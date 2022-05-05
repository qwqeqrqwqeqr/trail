package kr.ac.kgu.app.trail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.service.kakao.KakaoUserService
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.repository.AuthRepositoryImpl


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideAuthRepository (
        authService : AuthService,
        kakaoUserService: KakaoUserService,
        userInfoDao: UserInfoDao
    ): AuthRepository = AuthRepositoryImpl(authService,kakaoUserService,userInfoDao)

}