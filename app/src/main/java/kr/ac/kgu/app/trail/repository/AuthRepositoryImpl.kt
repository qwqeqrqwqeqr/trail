package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.data.remote.service.retrofit.AuthService
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.util.DataState

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dispatcherProvider: DispatcherProvider
) : AuthRepository {
    override suspend fun test(): Flow<DataState<Unit>> {
        TODO("Not yet implemented")
    }

}