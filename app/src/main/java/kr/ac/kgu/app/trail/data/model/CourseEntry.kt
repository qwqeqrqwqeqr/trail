package kr.ac.kgu.app.trail.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CourseEntry  (
    val courseId:Int ,
    val courseName: String ,
    val courseAddress: String ,
    val courseDistance: String ,
    val level : String ,
    val time : String
) : Parcelable


