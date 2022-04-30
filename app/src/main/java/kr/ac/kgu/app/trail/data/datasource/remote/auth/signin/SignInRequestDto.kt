package kr.ac.kgu.app.trail.data.datasource.remote.auth.signin

import com.google.gson.annotations.SerializedName

data class SignInRequestDto(
    @SerializedName("id")val id:String,
    @SerializedName("name")val name:String
)
