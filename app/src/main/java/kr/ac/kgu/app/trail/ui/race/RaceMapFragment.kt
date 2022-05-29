package kr.ac.kgu.app.trail.ui.race

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.SaveCourseInfo
import kr.ac.kgu.app.trail.databinding.FragmentRaceMapBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.util.Constants
import kr.ac.kgu.app.trail.util.Constants.PERMISSIONS_REQUEST_CODE
import kr.ac.kgu.app.trail.util.Constants.REQUIRED_PERMISSIONS
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.SensorHelper
import kr.ac.kgu.app.trail.util.toast
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import timber.log.Timber


@AndroidEntryPoint
class RaceMapFragment : BaseFragment<RaceMapViewModel, DataState<SaveCourseInfo>>(
    R.layout.fragment_race_map,
    RaceMapViewModel::class.java
), SensorEventListener {

    private lateinit var mapView : MapView
    private lateinit var sensorHelper: SensorHelper
    private val binding by viewBinding(FragmentRaceMapBinding::bind)
    private lateinit var locationManager: LocationManager
    private var stepCounter =0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkLocationService()) { permissionCheck() }
        initUi()
        initListener()


        sensorHelper = SensorHelper(requireContext(), Constants.TYPE_STEP_DETECTOR, this)
        sensorHelper.registerListener()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    private fun initListener() {
        binding.raceMapTraceBtn.setOnClickListener {
//            startTracking()

        }
    }

    private fun initUi(){
        mapView = MapView(requireActivity())
        binding.raceMapView.addView(mapView)

    }

    override fun updateUi(model: DataState<SaveCourseInfo>) {
        when (model) {
            is DataState.Success -> {
                binding.progressBar.isVisible = false
            }
            is DataState.Error -> {
                showErrorDialog()
                binding.progressBar.isVisible = false
            }
            DataState.Loading -> {
                binding.progressBar.isVisible = true
            }
        }
    }


//    // 현재 사용자 위치추적
//    @SuppressLint("MissingPermission")
//    private fun startTracking() {
//        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
//
//        val userNowLocation: Location? = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
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
//        mapView.addPOIItem(marker)
//    }





    private fun checkLocationService(): Boolean {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun permissionCheck() {
        val preference =  requireActivity().getPreferences(MODE_PRIVATE)
        val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
        if (ContextCompat.checkSelfPermission(requireActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한이 없는 상태
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    ACCESS_FINE_LOCATION)) {
                // 권한 거절
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
                builder.setPositiveButton("확인") { dialog, which ->
                    ActivityCompat.requestPermissions(requireActivity(),
                        REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
                }
                builder.setNegativeButton("취소") { dialog, which ->

                }
                builder.show()
            } else {
                if (isFirstCheck) {
                    // 최초 권한 요청
                    preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
                    ActivityCompat.requestPermissions(requireActivity(),
                        REQUIRED_PERMISSIONS,PERMISSIONS_REQUEST_CODE
                    )
                } else {
                    val builder = AlertDialog.Builder(requireActivity())
                    builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
                    builder.setPositiveButton("설정으로 이동") { dialog, which ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:kr.ac.kgu.app.trail"))
                        startActivity(intent)
                    }
                    builder.setNegativeButton("취소") { dialog, which ->

                    }
                    builder.show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.toast("위치 권한이 승인되었습니다")

            } else {
                this.toast("위치 권한이 거절되었습니다")

            }
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        stepCounter = (stepCounter + sensorEvent?.values!![0]).toInt()
        binding.stepDetectorText.text = stepCounter.toString()
        Timber.i("counter" + stepCounter)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }




}
