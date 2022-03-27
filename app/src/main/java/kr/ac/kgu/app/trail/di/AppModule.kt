package kr.ac.kgu.app.trail.di

import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

object AppModule {

//    @Singleton
//    @Provides
//    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
//        override val main: CoroutineDispatcher
//            get() = Dispatchers.Main
//        override val io: CoroutineDispatcher
//            get() = Dispatchers.IO
//        override val default: CoroutineDispatcher
//            get() = Dispatchers.Default
//        override val unconfined: CoroutineDispatcher
//            get() = Dispatchers.Unconfined
//    }
}