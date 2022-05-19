package kr.ac.kgu.app.trail.data.datasource.remote.auth.history

import com.google.gson.annotations.SerializedName

data class HistoryDto(
    @SerializedName("courseId")
    val courseId:Int,
    @SerializedName("courseName")
    val courseName: String,
    @SerializedName("courseAddress")
    val courseAddress: String,
    @SerializedName("courseDistance")
    val courseDistance: String,
    @SerializedName("level")
    val level : String,
    @SerializedName("time")
    val time : String
)
