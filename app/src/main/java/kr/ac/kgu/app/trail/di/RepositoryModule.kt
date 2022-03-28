package kr.ac.kgu.app.trail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.ac.kgu.app.trail.data.remote.api.retrofit.TrailService
import kr.ac.kgu.app.trail.repository.TrailRepository
import kr.ac.kgu.app.trail.repository.TrailRepositoryImpl


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        trailService : TrailService,
        dispatcherProvider: DispatcherProvider
    ): TrailRepository = TrailRepositoryImpl(trailService, dispatcherProvider)
}