package kr.ac.kgu.app.trail.data.service.trail

import kr.ac.kgu.app.trail.util.DataState
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.data.datastore.remote.auth.signin.SignInResponse
import kr.ac.kgu.app.trail.data.datastore.remote.auth.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

//    @GET("trail/course")
//    fun getCourse(@Query("id") format: Int): Call<Response>

    @GET("test/vise")
    suspend fun test(): Response<Unit>

    @GET("signIn")
    suspend fun signIn(
        @Body kakaoId: String,
        @Body name: String
    ): Response<SignInResponse>

    @POST("signUp")
    suspend fun signUp(
        @Body id: String,
        @Body name: String
    ): Response<SignUpResponse>

}
