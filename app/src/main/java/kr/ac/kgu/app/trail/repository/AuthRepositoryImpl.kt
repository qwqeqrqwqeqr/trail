package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpResponseDtoToId
import kr.ac.kgu.app.trail.data.model.ID
import kr.ac.kgu.app.trail.data.model.KakaoUserToSignUpRequestDto
import kr.ac.kgu.app.trail.data.model.Token
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


    override suspend fun signIn(): Flow<DataState<Unit>> = flow{
        emit(DataState.Loading)
    }



    override suspend fun signUp(): Flow<DataState<Unit>> =

        flow {
            emit(DataState.Loading)
            kakaoUserService.loginWithKakaoAccount()
            kakaoUserService.getUserInfo().collect {
                val response = authService.signUp(it.KakaoUserToSignUpRequestDto())
                if (response.isSuccessful) {
                       response.body()?.SignUpResponseDtoToId()
                } else {
                    emit(DataState.Error(response.message()))
                }
            }
        }

}