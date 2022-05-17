package kr.ac.kgu.app.trail.data.datasource.remote.auth.signin

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class SignInRequestDto(
    @SerializedName("snsId") var snsId: String,
    @SerializedName("name")val name:String
)

