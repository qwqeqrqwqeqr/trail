package kr.ac.kgu.app.trail.data.service.trail

import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInRequestDto
import retrofit2.http.GET
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {


    @GET("test/vise")
    suspend fun test(): Response<Unit>


    @POST("signIn")
    suspend fun signIn(@Body signInRequestDto: SignInRequestDto): Response<SignInResponseDto>

    @POST("signUp")
    suspend fun signUp(@Body signUpRequestDto: SignUpRequestDto): Response<SignUpResponseDto>




}
