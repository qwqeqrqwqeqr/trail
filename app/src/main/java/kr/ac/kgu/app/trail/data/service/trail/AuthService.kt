package kr.ac.kgu.app.trail.data.service.trail

import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInRequestDto
import retrofit2.http.GET
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

//    @GET("trail/course")
//    fun getCourse(@Query("id") format: Int): Call<Response>

    @GET("test/vise")
    suspend fun test(): Response<Unit>

    @GET("signIn")
    suspend fun signIn(signInRequestDto: SignInRequestDto): Response<SignInResponseDto>

    @POST("signUp")
    suspend fun signUp(signUpRequestDto: SignUpRequestDto): Response<SignUpResponseDto>

}
