package kr.ac.kgu.app.trail.data.datasource.remote.user.info

import com.google.gson.annotations.SerializedName
import kr.ac.kgu.app.trail.data.datasource.remote.course.course.GetCourseListResponseDto
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.UserInfo

data class UserInfoDto(
    @SerializedName("name")
    var name: String,
    @SerializedName("lastWorkDate")
    var lastWorkDate: Int,
    @SerializedName("distanceTotal")
    var distanceTotal: Int,
    @SerializedName("timeTotal")
    var timeTotal: Int,
    @SerializedName("stepCountTotal")
    var stepCountTotal: Int,
)

fun UserInfoDto.UserInfoDtoToUserInfo ()= UserInfo(
    name=name,
    lastWorkDate=lastWorkDate,
    distanceTotal=distanceTotal,
    timeTotal=timeTotal,
    stepCountTotal=stepCountTotal
)



