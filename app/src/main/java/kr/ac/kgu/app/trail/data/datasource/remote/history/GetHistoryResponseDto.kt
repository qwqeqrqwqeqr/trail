package kr.ac.kgu.app.trail.data.datasource.remote.history

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.DataTokenDto
import kr.ac.kgu.app.trail.data.datasource.remote.user.info.UserInfoDto
import kr.ac.kgu.app.trail.data.model.HistoryEntry

data class GetHistoryListResponseDto(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: String,
    @SerializedName("message")
    var meesage: String,
    @SerializedName("dataToken")
    var dataTokenDto: DataTokenDto?,
    @SerializedName("data")
    var data: HistoryContentDto
)

fun GetHistoryListResponseDto.historyDtoToHistoryEntry() = HistoryEntry(
    courseName = data.content.courseName,
    workStartTime = data.content.workStartTime,
    distance = data.content.distance,
    workComplete = data.content.workComplete,
    courseAddress = data.content.courseAddress,
)