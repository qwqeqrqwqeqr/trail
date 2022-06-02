package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.datasource.local.LocalDataConstants
import kr.ac.kgu.app.trail.data.datasource.local.dao.CourseDao
import kr.ac.kgu.app.trail.data.datasource.local.datastore.AppDataStore
import kr.ac.kgu.app.trail.data.datasource.local.entity.courseEntityToSaveCourseInfo
import kr.ac.kgu.app.trail.data.datasource.remote.course.course.courseDtoToCourseEntry
import kr.ac.kgu.app.trail.data.datasource.remote.course.detail.courseDetailDtoToCourseDetail
import kr.ac.kgu.app.trail.data.datasource.remote.course.savecourse.SaveCourseRequestDto
import kr.ac.kgu.app.trail.data.model.*
import kr.ac.kgu.app.trail.data.service.trail.AuthService
import kr.ac.kgu.app.trail.data.service.trail.TrailService

import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

interface CourseRepository {
    suspend fun getCourseList(): Flow<DataState<List<CourseEntry>>>
    suspend fun saveTempCourse(courseEntry: CourseEntry): Flow<DataState<Unit>>
    suspend fun loadTempCourse(): Flow<DataState<SaveCourseInfo>>
    suspend fun getCourseDetail(): Flow<DataState<CourseDetail>>
    suspend fun saveCourse(saveCourseInfo: SaveCourseInfo): Flow<DataState<Unit>>
}

class CourseRepositoryImpl @Inject constructor(
    private val trailService: TrailService,
    private val appDataStore: AppDataStore,
    private val courseDao: CourseDao
) : CourseRepository {

    override suspend fun getCourseList(): Flow<DataState<List<CourseEntry>>> = flow {
        emit(DataState.Loading)
        val response = trailService.getCourseList("","","","")
        if (response.isSuccessful) {
            emit(DataState.Success(response.body()?.courseDtoToCourseEntry()?.sortedByDescending(){it.courseId}
                ?: emptyList()))
        } else {
            emit(DataState.Error(response.body()?.meesage.toString()))
            Timber.i("getCourseList response is success: "+response.body()?.success)
            Timber.i("getCourseList response code: "+response.body()?.status)
            Timber.i("getCourseList response message: "+response.body()?.meesage)
        }
    }

    override suspend fun saveTempCourse(courseEntry: CourseEntry): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading)
        courseDao.insertCourse(courseEntry.courseEntryToCourseEntity())
        appDataStore.setInt(LocalDataConstants.COURSE_ID,courseEntry.courseId)
        emit(DataState.Success(Unit))
        Timber.i("saveTempCourse Success ")
        Timber.i("saveTempCourse courseId " + courseEntry.courseId)
        Timber.i("saveTempCourse courseName " + courseEntry.courseName)
        Timber.i("saveTempCourse courseAddress " + courseEntry.courseAddress)
    }

    override suspend fun loadTempCourse(): Flow<DataState<SaveCourseInfo>> = flow {
        emit(DataState.Loading)
        appDataStore.readInt(LocalDataConstants.COURSE_ID).collect{
           emit(DataState.Success(courseDao.getCourse(it).courseEntityToSaveCourseInfo()))
            Timber.i("loadTempCourse Success ")
            Timber.i("loadTempCourse courseId $it")
        }
    }

    override suspend fun getCourseDetail(): Flow<DataState<CourseDetail>> = flow{
        emit(DataState.Loading)
        appDataStore.readInt(LocalDataConstants.COURSE_ID).collect {
            val response = trailService.getCourseDetail(it)
            if (response.isSuccessful) {
                emit(DataState.Success(response.body()!!.courseDetailDtoToCourseDetail()))
                Timber.i("getAddressList response is success: " + response.body()?.success)
                Timber.i("getAddressList response code: " + response.body()?.status)
                Timber.i("getAddressList response message: " + response.body()?.meesage)
            } else {
                emit(DataState.Error(response.body()?.meesage.toString()))
                Timber.i("getCourseList response is success: " + response.body()?.success)
                Timber.i("getCourseList response code: " + response.body()?.status)
                Timber.i("getCourseList response message: " + response.body()?.meesage)
            }
        }
    }

    override suspend fun saveCourse(saveCourseInfo: SaveCourseInfo): Flow<DataState<Unit>> = flow{
        emit(DataState.Loading)
            val response = trailService.saveCourse(saveCourseInfo.saveCourseInfoToSaveCourseDto())
            if (response.isSuccessful) {
                emit(DataState.Success(Unit))
                Timber.i("saveCourse response is success: " + response.body()?.success)
                Timber.i("saveCourse response code: " + response.body()?.status)
                Timber.i("saveCourse response message: " + response.body()?.meesage)
            } else {
                emit(DataState.Error(response.body()?.meesage.toString()))
                Timber.i("saveCourse response is success: " + response.body()?.success)
                Timber.i("saveCourse response code: " + response.body()?.status)
                Timber.i("saveCourse response message: " + response.body()?.meesage)
            }

    }
    



}
