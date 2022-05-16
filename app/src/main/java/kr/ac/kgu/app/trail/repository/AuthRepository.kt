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
import kr.ac.kgu.app.trail.data.datasource.local.entity.KakaoUserInfoToSignUpRequestDto
import kr.ac.kgu.app.trail.data.model.*
import kr.ac.kgu.app.trail.data.service.kakao.KakaoService
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject
import timber.log.Timber

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
//        if (kakaoUserService.kakaoHasToken()) {
//            val id = appDataStore.getId()
//            userInfoDao.getUserinfo(id.toString())
//        }
    }
    @SuppressLint("CheckResult")
    override suspend fun signUp(): Flow<DataState<Unit>> =

        flow {
            emit(DataState.Loading)
            val response = authService.signUp(userInfoDao.getUserinfo()[0].KakaoUserInfoToSignUpRequestDto())
            if (response.isSuccessful) {
                appDataStore.setId(response.body()?.data.toString())
                emit(DataState.Success(Unit))
            } else {
                emit(DataState.Error(response.message()))
            }

        }

}