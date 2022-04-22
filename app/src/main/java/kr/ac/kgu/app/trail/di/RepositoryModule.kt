package kr.ac.kgu.app.trail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.ac.kgu.app.trail.data.remote.service.retrofit.AuthService
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.repository.AuthRepositoryImpl


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        authService : AuthService,
        dispatcherProvider: DispatcherProvider
    ): AuthRepository = AuthRepositoryImpl(authService, dispatcherProvider)
}