package kr.ac.kgu.app.trail.data.datasource.remote.history

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.DataTokenDto
import kr.ac.kgu.app.trail.data.datasource.remote.course.course.GetCourseListResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.user.info.UserInfoDto
import kr.ac.kgu.app.trail.data.model.CourseEntry
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
    var data: HistoryContentDto?
)

fun GetHistoryListResponseDto.historyDtoToHistoryEntry()  : List<HistoryEntry>{
    return data?.content?.map {
        HistoryEntry(
            courseName = it.courseName,
            workStartTime = it.workStartTime,
            distance = it.distance,
            workComplete = it.workComplete,
            courseAddress = it.courseAddress,
        )
    } ?: emptyList()
}