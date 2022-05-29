package kr.ac.kgu.app.trail.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveCourseInfo(
    val courseName:String,
    val courseAddress: String,
    val workStartTime: String = "",
    val workFinishTime: String ="",
    val distance : Int,
    val stepCount : Int =0,
    val workTime : Int =0
): Parcelable

