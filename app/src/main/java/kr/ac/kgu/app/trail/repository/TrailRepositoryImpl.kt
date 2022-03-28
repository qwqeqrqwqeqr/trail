package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.data.remote.api.retrofit.TrailService
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.util.DataState

class TrailRepositoryImpl(
    private val trailService: TrailService,
    private val dispatcherProvider : DispatcherProvider
    ):TrailRepository {

    override suspend fun test(): Flow<DataState<Unit>> = trailService.test()
}


//    override suspend fun getUser(): Flow<DataState<User?>> = flow {
//        emit(DataState.Loading)
//        val userCache = userDao.getUser()
//        if (userCache != null) {
//            emit(DataState.Success(userMapper.mapFromEntity(userCache)))
//        } else {
//            emit(DataState.Error())
//        }
//}