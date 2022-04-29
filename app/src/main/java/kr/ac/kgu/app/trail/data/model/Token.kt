package kr.ac.kgu.app.trail.data.model

import com.google.gson.annotations.SerializedName

data class Token(
    var accessToken: String,
    var refreshToken: String
)