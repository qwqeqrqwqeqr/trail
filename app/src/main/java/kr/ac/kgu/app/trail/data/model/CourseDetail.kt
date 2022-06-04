package kr.ac.kgu.app.trail.data.model

data class CourseDetail(
    val courseDetailId : Int,
    val courseCoordinateList: LinkedHashMap<String,String>,
    val facilityList: List<Facility>
)

data class Facility(
    val facilityId : Int,
    val facilityName : String,
    val type : String,
    val coordinate: Map<String,String>
)
