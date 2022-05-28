package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.datasource.remote.auth.course.courseDtoToCourseEntry
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.data.service.trail.TrailService

import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

interface CourseRepository {
    suspend fun getCourseList(): Flow<DataState<List<CourseEntry>>>
}

class CourseRepositoryImpl @Inject constructor(
    private val trailService: TrailService,
    private val appDataStore: AppDataStore
) : CourseRepository {

    override suspend fun getCourseList(): Flow<DataState<List<CourseEntry>>> = flow {
        emit(DataState.Loading)
        val response = trailService.getCourseList(testCode)
        if (response.isSuccessful) {
            emit(DataState.Success(response.body()?.courseDtoToCourseEntry()?.sortedByDescending(){it.courseId}
                ?: emptyList()))
        } else {
            emit(DataState.Error(response.body()?.meesage.toString()))
            Timber.i("getCourseList response is success?: "+response.body()?.success)
            Timber.i("getCourseList response code: "+response.body()?.status)
            Timber.i("getCourseList response message: "+response.body()?.meesage)
        }
    }
}


const val testCode = "중"