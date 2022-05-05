package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.model.KakaoUserToSignUpRequestDto
import kr.ac.kgu.app.trail.data.model.UserInfo
import kr.ac.kgu.app.trail.data.model.UserInfoToUserInfoEntity
import kr.ac.kgu.app.trail.data.service.kakao.KakaoUserService
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val kakaoUserService: KakaoUserService,
    private val userInfoDao: UserInfoDao
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
                        userInfoDao.insertUserinfo(UserInfo(response.body()?.data!!, it.id.toString(),it.email,it.nickname,it.thumbnailImageUrl).UserInfoToUserInfoEntity())
                        emit(DataState.Success(Unit))
                } else {
                    emit(DataState.Error(response.message()))
                }
            }
        }

}