package kr.ac.kgu.app.trail.data.datasource.remote.course.course

import com.google.gson.annotations.SerializedName

data class CourseDto(
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

