package kr.ac.kgu.app.trail.data.datasource.remote.course.detail

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.DataTokenDto
import kr.ac.kgu.app.trail.data.datasource.remote.course.course.GetCourseListResponseDto
import kr.ac.kgu.app.trail.data.datasource.remote.history.HistoryContentDto
import kr.ac.kgu.app.trail.data.model.*

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
            when(it.type){
                OBSTACLE -> FacilityType.OBSTACLE
                TOILET -> FacilityType.TOILET
                CHARGE -> FacilityType.CHARGE
                STAIR -> FacilityType.STAIR
                else -> FacilityType.SLOPE
            },
            Coordinate(it.coordinate.x.toDouble(),it.coordinate.y.toDouble())
        )
    }.toList()
    val coordinateArray = data.coordinateArray.map { Coordinate(it.x.toDouble(),it.y.toDouble()) }.toList()
    return CourseDetail(
        courseDetailId = data.courseDetailId,
        coordinateArray,
        facilityList
    )
}
const val  OBSTACLE = "OBSTACLE"
const val  TOILET = "TOILET"
const val  CHARGE = "CHARGE"
const val  STAIR = "STAIR"
const val  SLOPE = "SLOPE"
