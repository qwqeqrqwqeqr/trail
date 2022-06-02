package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.dao.CourseDao
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.HistoryEntry
import kr.ac.kgu.app.trail.data.service.trail.TrailService
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

interface HistoryRepository {
    suspend fun getHistoryList(): Flow<DataState<List<HistoryEntry>>>
}

class HistoryRepositoryImpl @Inject constructor(
    private val trailService: TrailService,
) : HistoryRepository {
    override suspend fun getHistoryList(): Flow<DataState<List<HistoryEntry>>> = flow{
        emit(DataState.Loading)
        val response = trailService.getHistoryList()

    }

}
