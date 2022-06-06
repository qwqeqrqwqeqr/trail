package kr.ac.kgu.app.trail.data.model

import com.google.gson.annotations.SerializedName

data class  HistoryEntry(
    var courseName: String,
    var workStartTime: String,
    var distance: Int,
    var workComplete: Boolean?,
    var courseAddress: String?
)
