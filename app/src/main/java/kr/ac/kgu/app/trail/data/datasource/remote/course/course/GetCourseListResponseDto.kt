package kr.ac.kgu.app.trail.data.datasource.remote.course.course

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.DataTokenDto

import kr.ac.kgu.app.trail.data.model.CourseEntry

data class GetCourseListResponseDto(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: String,
    @SerializedName("message")
    var meesage: String,
    @SerializedName("dataToken")
    var dataTokenDto: DataTokenDto?,
    @SerializedName("data")
    var data: List<CourseDto>?
)


fun GetCourseListResponseDto.courseDtoToCourseEntry(): List<CourseEntry> {
    return data?.map {
        CourseEntry(
            courseId = it.courseId,
            courseName = it.courseName,
            courseAddress = it.courseAddress,
            courseDistance = it.courseDistance,
            level = it.level,
            time = it.time,
        )
    } ?: emptyList()

}
