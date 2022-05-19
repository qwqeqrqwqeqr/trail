package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.data.model.CourseEntry

import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

interface CourseRepository{
    suspend fun getAllCourseList(): Flow<DataState<List<CourseEntry>>>
}
class CourseRepositoryImpl @Inject constructor():CourseRepository{
    override suspend fun getAllCourseList(): Flow<DataState<List<CourseEntry>>> = flow{
        emit(DataState.Loading)
    }
}