package kr.ac.kgu.app.trail.data.datasource.local.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpRequestDto
import kr.ac.kgu.app.trail.data.model.SaveCourseInfo
import kr.ac.kgu.app.trail.util.parser.getCurrentDate

/*
* Param
* courseId : course 고유 id
* courseName : course의 이름 (중복되는 이름이 존재할 수 있음)
* courseAddress : course의 실제 주소
* level : course의 난이도
* time : course 예상 산책 시간
* distance : course 의 거리
* */

@Entity(tableName = "course")
@Keep
data class CourseEntity(
    @PrimaryKey
    @ColumnInfo(name = "courseId")
    val courseId: Int,

    @ColumnInfo(name = "courseName")
    val courseName: String,

    @ColumnInfo(name = "courseAddress")
    val courseAddress: String,

    @ColumnInfo(name = "level")
    val level: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "distance")
    val distance: Int,
)

fun CourseEntity.courseEntityToSaveCourseInfo(): SaveCourseInfo = SaveCourseInfo(
    courseName = courseName,
    courseAddress = courseAddress,
    distance = distance
)


