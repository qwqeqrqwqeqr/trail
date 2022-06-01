package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.remote.user.info.UserInfoDtoToUserInfo
import kr.ac.kgu.app.trail.data.model.UserInfo
import kr.ac.kgu.app.trail.data.model.UserToken
import kr.ac.kgu.app.trail.data.service.trail.TrailService
import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

interface UserRepository {
    suspend fun getAddressList() : Flow<DataState<List<String>>>
    suspend fun saveAddress(address: String) : Flow<DataState<Unit>>
    suspend fun getUserInfo() : Flow<DataState<UserInfo>>
}

class UserRepositoryImpl @Inject constructor(
    private val trailService: TrailService
) :UserRepository{
    override suspend fun getAddressList(): Flow<DataState<List<String>>> = flow {
        emit(DataState.Loading)
        val response = trailService.getAddressList()
        if(response.isSuccessful){
            emit(DataState.Success(response.body()!!.data))
            Timber.i("getAddressList response is success: "+response.body()?.success)
            Timber.i("getAddressList response code: "+response.body()?.status)
            Timber.i("getAddressList response message: "+response.body()?.meesage)
        }else{
            emit(DataState.Error(response.body()?.meesage.toString()))
            Timber.i("getAddressList response is success: "+response.body()?.success)
            Timber.i("getAddressList response code: "+response.body()?.status)
            Timber.i("getAddressList response message: "+response.body()?.meesage)
        }
    }

    override suspend fun saveAddress(address: String): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading)
        Timber.i("saveAddress address info: $address")
        Timber.i("saveAddress accessToken info: "+ UserToken.accessToken)
        val response = trailService.saveAddress(address)
        if(response.isSuccessful){
            emit(DataState.Success(Unit))
            Timber.i("saveAddress response is success: "+response.body()?.success)
            Timber.i("saveAddress response code: "+response.body()?.status)
            Timber.i("saveAddress response message: "+response.body()?.meesage)
        }else{
            emit(DataState.Error(response.body()?.meesage.toString()))
        }

    }

    override suspend fun getUserInfo(): Flow<DataState<UserInfo>> = flow {
        emit(DataState.Loading)
        val response = trailService.getUserInfo()
        if(response.isSuccessful){
            emit(DataState.Success(response.body()?.data?.UserInfoDtoToUserInfo()!!))
            Timber.i("getUserInfo response is success: "+response.body()?.success)
            Timber.i("getUserInfo response code: "+response.body()?.status)
            Timber.i("getUserInfo response message: "+response.body()?.meesage)
        } else{
            emit(DataState.Error(response.body()?.meesage.toString()))
            Timber.i("getUserInfo response is success: "+response.body()?.success)
            Timber.i("getUserInfo response code: "+response.body()?.status)
            Timber.i("getUserInfo response message: "+response.body()?.meesage)
        }
    }
}