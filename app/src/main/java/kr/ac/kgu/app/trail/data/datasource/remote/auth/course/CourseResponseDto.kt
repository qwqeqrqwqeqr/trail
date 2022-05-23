package kr.ac.kgu.app.trail.data.datasource.remote.auth.course

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.auth.DataTokenDto

data class CourseResponseDto(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: String,
    @SerializedName("message")
    var meesage: String,
    @SerializedName("dataToken")
    var dataTokenDto: DataTokenDto?,
    @SerializedName("data")
    var data: List<CourseDto>?
)
