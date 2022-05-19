package kr.ac.kgu.app.trail.data.model

import android.os.Parcel

import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


data class CourseEntry(
    val courseId:Int,
    val courseName: String,
    val courseAddress: String,
    val courseDistance: String,
    val level : String,
    val time : String
)
