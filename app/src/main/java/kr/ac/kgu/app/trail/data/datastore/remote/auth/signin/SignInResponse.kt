package kr.ac.kgu.app.trail.data.datastore.remote.auth.signin

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datastore.remote.auth.DataToken

data class SignInResponse(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: String,
    @SerializedName("message")
    var meesage: String,
    @SerializedName("dataToken")
    var dataToken: DataToken?,
    @SerializedName("data")
    var data: Int?
)
