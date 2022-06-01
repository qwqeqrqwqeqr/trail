package kr.ac.kgu.app.trail.data.datasource.remote.course.detail

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.DataTokenDto
import kr.ac.kgu.app.trail.data.datasource.remote.course.course.GetCourseListResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.history.HistoryContentDto
import kr.ac.kgu.app.trail.data.model.CourseDetail
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.Facility

data class GetCourseDetailResponseDto(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: String,
    @SerializedName("message")
    var meesage: String,
    @SerializedName("dataToken")
    var dataTokenDto: DataTokenDto?,
    @SerializedName("data")
    var data: CourseDetailDto
)

fun GetCourseDetailResponseDto.courseDetailDtoToCourseDetail(): CourseDetail {
    val facilityList = data.facilityDtoList.map {
        Facility(
            it.facilityId,
            it.facilityName,
            it.type,
            it.coordinate
        )
    }.toList()
    return CourseDetail(
        courseDetailId = data.courseDetailId,
        courseCoordinateList = data.coordinateArray,
        facilityList
    )
}