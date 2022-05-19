package kr.ac.kgu.app.trail.data.service.trail

import kr.ac.kgu.app.trail.data.datasource.remote.auth.history.HistoryResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrailService {


    @GET("courseList")
    suspend fun getCourseList(@Query("courseAddress") courseAddress: String): Response<HistoryResponseDto>
}