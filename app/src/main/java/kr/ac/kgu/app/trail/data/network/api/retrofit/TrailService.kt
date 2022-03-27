package kr.ac.kgu.app.trail.data.network.api.retrofit

import dagger.Module
import dagger.hilt.InstallIn
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrailService {

//    @GET("trail/course")
//    fun getCourse(@Query("id") format: Int): Call<Response>

    @GET("/test")
    suspend fun test(): Response<Int>

}
