package kr.ac.kgu.app.trail.data.datasource.remote.auth

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.model.UserToken

data class DataTokenDto(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("refresh_token")
    var refreshToken: String,
)

fun DataTokenDto.dataTokenDtoToUserToken(){
    UserToken.accessToken = accessToken
    UserToken.refreshToken= refreshToken
}