package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.model.ID
import kr.ac.kgu.app.trail.data.model.Token

import kr.ac.kgu.app.trail.data.model.toSignUpRequsetDto
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


    override suspend fun signIn(): Flow<DataState<Token>> =
        flow {
            emit(DataState.Loading)
            kakaoUserService.loginWithKakaoAccount()
            kakaoUserService.getUserInfo().collect{
                    authService.signUp(it.toSignUpRequsetDto())
            }


        }





    override suspend fun signUp(): Flow<DataState<ID>> {
        TODO("Not yet implemented")
    }

}