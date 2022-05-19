package kr.ac.kgu.app.trail.data.datasource.remote.auth.history

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.auth.DataTokenDto

data class HistoryResponseDto(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: String,
    @SerializedName("message")
    var meesage: String,
    @SerializedName("dataToken")
    var dataTokenDto: DataTokenDto?,
    @SerializedName("data")
    var data: List<HistoryDto>?
)
