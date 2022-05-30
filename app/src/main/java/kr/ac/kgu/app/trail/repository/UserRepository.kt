package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.service.trail.TrailService
import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

interface UserRepository {
    suspend fun getAddressList() : Flow<DataState<List<String>>>
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
}