package kr.ac.kgu.app.trail.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.RxUserApiClient
import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.datasource.local.entity.kakaoUserInfoEntityToSignInRequestDto
import kr.ac.kgu.app.trail.data.datasource.local.entity.kakaoUserInfoEntityToSignUpRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.dataTokenDtoToUserToken
import kr.ac.kgu.app.trail.data.model.*
import kr.ac.kgu.app.trail.data.service.kakao.KakaoService
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

interface AuthRepository {
    suspend fun signIn(): Flow<DataState<Unit>>
    suspend fun signUp(): Flow<DataState<Unit>>

}


class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val userInfoDao: UserInfoDao,
    private val appDataStore: AppDataStore
) : AuthRepository {


    override suspend fun signIn(): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading)
        userInfoDao.getUserinfo().last().kakaoUserInfoEntityToSignInRequestDto()
        val response = authService.signIn(SignInRequestDto("100","sangsang"))
        if(response.isSuccessful){
            Timber.i("signIn response is success?: "+response.body()?.success)
            Timber.i("signIn response code: "+response.body()?.status)
            Timber.i("signIn response message: "+response.body()?.meesage)
            Timber.i("signIn response id: "+response.body()?.data)
            Timber.i("signIn response accessToken: "+ response.body()?.dataTokenDto!!.accessToken)
            Timber.i("signIn response refreshToken: "+ response.body()?.dataTokenDto!!.refreshToken)
            response.body()?.dataTokenDto?.dataTokenDtoToUserToken()
            emit(DataState.Success(Unit))
        }else{
            emit(DataState.Error(response.body()?.meesage.toString()))
            Timber.i("signIn response is success?: "+response.body()?.success)
            Timber.i("signIn response code: "+response.body()?.status)
            Timber.i("signIn response message: "+response.body()?.meesage)
        }
    }
    @SuppressLint("CheckResult")
    override suspend fun signUp(): Flow<DataState<Unit>> =

        flow {
            emit(DataState.Loading)
            val response = authService.signUp(userInfoDao.getUserinfo().last().kakaoUserInfoEntityToSignUpRequestDto())
            if (response.isSuccessful) {
                Timber.i("signUp response is success?: "+response.body()?.success)
                Timber.i("signUp response code: "+response.body()?.status)
                Timber.i("signUp response message: "+response.body()?.meesage)
                Timber.i("signUp response id: "+response.body()?.data)
                  emit(DataState.Success(Unit))
            } else {
                emit(DataState.Error(response.body()?.meesage.toString()))
                 Timber.i("signUp response is success?: "+response.body()?.success)
                Timber.i("signUp response code: "+response.body()?.status)
                Timber.i("signUp response message: "+response.body()?.meesage)
            }
        }

}