package kr.ac.kgu.app.trail.data.datasource.remote.history

import com.google.gson.annotations.SerializedName

data class HistoryDto(
    @SerializedName("courseName")
    var courseName: String,
    @SerializedName("workStartTime")
    var workStartTime: String,
    @SerializedName("distance")
    var distance: Int,
    @SerializedName("workComplete")
    var workComplete: Boolean?,
    @SerializedName("courseAddress")
    var courseAddress: String?
)
