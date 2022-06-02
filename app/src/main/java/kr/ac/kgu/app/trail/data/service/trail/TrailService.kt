package kr.ac.kgu.app.trail.data.service.trail

import kr.ac.kgu.app.trail.data.datasource.remote.course.course.GetCourseListResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.course.detail.GetCourseDetailResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.course.savecourse.SaveCourseRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.course.savecourse.SaveCourseResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.history.GetHistoryListResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.user.address.GetAddressListResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.user.address.SaveAddressResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.user.info.GetUserInfoResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TrailService {

    @GET("course")
    suspend fun getCourseList(
        @Query("level") level: String,
        @Query("toilet") toilet: String,
        @Query("charge") charge: String,
        @Query("thirtyMore") thirtyMore: String
    ): Response<GetCourseListResponseDto>


    @POST("user/courseSave")
    suspend fun saveCourse(
        @Body saveCourseRequestDto: SaveCourseRequestDto
    ): Response<SaveCourseResponseDto>

    @GET("address")
    suspend fun getAddressList(): Response<GetAddressListResponseDto>

    @POST("userLocation")
    suspend fun saveAddress(@Query("location") location: String): Response<SaveAddressResponseDto>

    @GET("detail")
    suspend fun getCourseDetail(@Query("courseId") courseId: Int) : Response<GetCourseDetailResponseDto>

    @GET("user/view/myPage")
    suspend fun getUserInfo() : Response<GetUserInfoResponseDto>

    @GET("user/history/view")
    suspend fun getHistoryList() : Response<GetHistoryListResponseDto>



}