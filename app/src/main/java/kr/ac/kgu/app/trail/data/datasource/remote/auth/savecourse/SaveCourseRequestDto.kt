package kr.ac.kgu.app.trail.data.datasource.remote.auth.savecourse

import com.google.gson.annotations.SerializedName

/*
* Param
*  courseName : course의 이름 (중복되는 이름이 존재할 수 있음)
* courseAddress : course의 실제 주소
* stepCount : course를 산책하면서 발생한 걸음 수 (코스 완료시 산정된다.)
* workTime : course를 산책하면서 실제 걸린 시간 이때 단위는 분 단위 (workStartTime, workFinishTime의 차이로 구한다)
* workStartTime : course를 산책을 시작할 때의 시간
* workFinishTime : course의 끝나는 시간 끝날 때 계산한다.
 */

data class SaveCourseRequestDto(
    @SerializedName("courseName")
    val courseName:String,
    @SerializedName("courseAddress")
    val courseAddress: String,
    @SerializedName("workStartTime")
    val workStartTime: String,
    @SerializedName("workFinishTime")
    val workFinishTime: String,
    @SerializedName("distance")
    val distance : Int,
    @SerializedName("stepCount")
    val stepCount : Int,
    @SerializedName("workTime")
    val workTime : Int
)
