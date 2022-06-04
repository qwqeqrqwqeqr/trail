package kr.ac.kgu.app.trail.data.datasource.remote.course.detail

import com.google.gson.annotations.SerializedName

data class FacilityDto(
    @SerializedName("facilityId")
    var facilityId: Int,
    @SerializedName("facilityName")
    var facilityName: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("coordinate")
    var coordinate: LinkedHashMap<String,String>,
)


