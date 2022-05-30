package kr.ac.kgu.app.trail.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kr.ac.kgu.app.trail.data.datasource.local.entity.CourseEntity

@Parcelize
class CourseEntry(
    val courseId: Int,
    val courseName: String,
    val courseAddress: String,
    val courseDistance: String,
    val level: String,
    val time: String
) : Parcelable


fun CourseEntry.courseEntryToCourseEntity(): CourseEntity {
    return CourseEntity(
        courseId = courseId,
        courseName = courseName,
        courseAddress = courseAddress,
        distance = courseDistanceToInt(courseDistance),
        level = level,
        time = time
    )
}

fun courseDistanceToInt(courseDistance: String): Int = courseDistance.dropLast(1).toInt()

