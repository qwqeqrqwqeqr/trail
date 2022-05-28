package kr.ac.kgu.app.trail.ui.race

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.databinding.FragmentRaceMapBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.viewBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapView


@AndroidEntryPoint
class RaceMapFragment : BaseFragment<RaceMapViewModel, CourseEntry>(
    R.layout.fragment_race_map,
    RaceMapViewModel::class.java
) {


    private val binding by viewBinding(FragmentRaceMapBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()


    }

    private fun initUi(){
        val mapView = MapView(requireActivity())
        binding.raceMapView.addView(mapView)

    }

    override fun updateUi(model: CourseEntry) {

    }

//    @SuppressLint("MissingPermission", "ServiceCast")
//    private fun startTracking() {
//        binding.raceMapView.currentLocationTrackingMode =
//            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading  //이 부분
//
//        val lm: LocationManager = getSystemService(requireContext().LOCATION_SERVICE) as LocationManager
//        val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//        //위도 , 경도
//        val uLatitude = userNowLocation?.latitude
//        val uLongitude = userNowLocation?.longitude
//        val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)
//
//        // 현 위치에 마커 찍기
//        val marker = MapPOIItem()
//        marker.itemName = "현 위치"
//        marker.mapPoint =uNowPosition
//        marker.markerType = MapPOIItem.MarkerType.BluePin
//        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
//        binding.raceMapView.addPOIItem(marker)
//    }


}