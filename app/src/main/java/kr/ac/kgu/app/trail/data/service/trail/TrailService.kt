package kr.ac.kgu.app.trail.data.service.trail

import kr.ac.kgu.app.trail.data.datasource.remote.auth.course.CourseResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.savecourse.SaveCourseRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.savecourse.SaveCourseResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TrailService {

    @GET("courseList")
    suspend fun getCourseList(
        @Query("courseAddress") attribute : String
    ): Response<CourseResponseDto>


    @POST("user/courseSave")
    suspend fun saveCourse(
        @Body saveCourseRequestDto: SaveCourseRequestDto
    ): Response<SaveCourseResponseDto>

}