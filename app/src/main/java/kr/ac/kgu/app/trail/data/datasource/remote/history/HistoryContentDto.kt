package kr.ac.kgu.app.trail.data.datasource.remote.history

import com.google.gson.annotations.SerializedName

data class HistoryContentDto(
    @SerializedName("content")
    var content: HistoryDto,
)
