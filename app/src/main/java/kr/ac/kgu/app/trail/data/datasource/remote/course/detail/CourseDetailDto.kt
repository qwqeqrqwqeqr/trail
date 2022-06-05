package kr.ac.kgu.app.trail.data.datasource.remote.course.detail

import com.google.gson.annotations.SerializedName

data class CourseDetailDto(
    @SerializedName("courseDetailId")
    var courseDetailId: Int,
    @SerializedName("coordinateArray")
    var coordinateArray : List<CoordinateDto>,
    @SerializedName("facilityDtoList")
    var facilityDtoList : List<FacilityDto>
)


