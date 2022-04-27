package kr.ac.kgu.app.trail.data.datastore.remote.auth

import com.google.gson.annotations.SerializedName

data class DataToken(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("refresh_token")
    var refreshToken: String,
)
