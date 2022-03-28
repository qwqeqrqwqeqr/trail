package kr.ac.kgu.app.trail.data.remote.api.retrofit

import kr.ac.kgu.app.trail.util.DataState
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow

interface TrailService {

//    @GET("trail/course")
//    fun getCourse(@Query("id") format: Int): Call<Response>

    @GET("test/vise")
    suspend fun test(): Flow<DataState<Unit>>

}
