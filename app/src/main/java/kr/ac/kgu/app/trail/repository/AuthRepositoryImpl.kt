package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datastore.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.model.IDEntity
import kr.ac.kgu.app.trail.data.model.TokenEntity
import kr.ac.kgu.app.trail.data.service.kakao.KakaoUserService
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.util.DataState

class AuthRepositoryImpl constructor(
    private val authService: AuthService,
    private val kakaoUserService: KakaoUserService,
    private val dispatcherProvider: DispatcherProvider,
    private val appDataStore: AppDataStore
) : AuthRepository {
    override suspend fun signIn(): Flow<DataState<TokenEntity>> =
        flow {
            emit(DataState.Loading)
            kakaoUserService.loginWithKakaoAccount()

        }


    override suspend fun signUp(): Flow<DataState<IDEntity>> {
        TODO("Not yet implemented")
    }

}