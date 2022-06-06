package kr.ac.kgu.app.trail.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kr.ac.kgu.app.trail.data.datasource.remote.course.savecourse.SaveCourseRequestDto

@Parcelize
data class SaveCourseInfo(
    val courseName:String,
    val courseAddress: String,
    var workStartTime: String = "",
    var workFinishTime: String ="",
    val distance : Int,
    var stepCount : Int =0,
    var workTime : Int =0
): Parcelable

fun SaveCourseInfo.saveCourseInfoToSaveCourseDto() = SaveCourseRequestDto(
    courseName=courseName,
    courseAddress=courseAddress,
    workStartTime = workStartTime,
    workFinishTime = workFinishTime,
    distance = distance,
    stepCount = distance,
    workTime= workTime
)

