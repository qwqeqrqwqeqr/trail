package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.dao.CourseDao
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.datasource.remote.course.course.courseDtoToCourseEntry
import kr.ac.kgu.app.trail.data.datasource.remote.history.historyDtoToHistoryEntry
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.HistoryEntry
import kr.ac.kgu.app.trail.data.service.trail.TrailService
import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

interface HistoryRepository {
    suspend fun getHistoryList(): Flow<DataState<List<HistoryEntry>>>
}

class HistoryRepositoryImpl @Inject constructor(
    private val trailService: TrailService,
) : HistoryRepository {
    override suspend fun getHistoryList(): Flow<DataState<List<HistoryEntry>>> = flow {
        emit(DataState.Loading)
        val response = trailService.getHistoryList()
        if (response.isSuccessful) {
            emit(DataState.Success(response.body()?.historyDtoToHistoryEntry()
                ?: emptyList()))
            Timber.i("getHistoryList Success ")

        } else {
            emit(DataState.Error(response.body()?.meesage.toString()))
            Timber.i("getHistoryList response is success: "+response.body()?.success)
            Timber.i("getHistoryList response code: "+response.body()?.status)
            Timber.i("getHistoryList response message: "+response.body()?.meesage)
        }
    }

}