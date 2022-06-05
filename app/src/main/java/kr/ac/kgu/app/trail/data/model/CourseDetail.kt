package kr.ac.kgu.app.trail.data.model


data class CourseDetail(
    val courseDetailId : Int,
    val courseCoordinateList: List<Coordinate>,
    val facilityList: List<Facility>
)

data class Facility(
    val facilityId : Int,
    val facilityName : String,
    val type : FacilityType,
    val coordinate: Coordinate
)

data class Coordinate(
    val x : Double,
    val y : Double,
)

enum class FacilityType {
    OBSTACLE,
    TOILET,
    CHARGE,
    STAIR,
    SLOPE
}

