package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.service.trail.TrailService
import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

interface UserRepository {
    suspend fun getAddressList() : Flow<DataState<List<String>>>
    suspend fun saveAddress(address: String) : Flow<DataState<Unit>>
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
        }
    }

    override suspend fun saveAddress(address: String): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading)
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
}