package kr.ac.kgu.app.trail.data.datasource.remote.course.detail

import com.google.gson.annotations.SerializedName

data class CoordinateDto(
    @SerializedName("x")
    var x: String,
    @SerializedName("y")
    var y: String,
)
