package kr.ac.kgu.app.trail.ui.race

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.Coordinate
import kr.ac.kgu.app.trail.data.model.Facility
import kr.ac.kgu.app.trail.data.model.FacilityType
import kr.ac.kgu.app.trail.data.model.SaveCourseInfo
import kr.ac.kgu.app.trail.databinding.FragmentRaceMapBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.ui.main.MainActivity
import kr.ac.kgu.app.trail.util.Constants
import kr.ac.kgu.app.trail.util.Constants.PERMISSIONS_REQUEST_CODE
import kr.ac.kgu.app.trail.util.Constants.REQUIRED_PERMISSIONS
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.SensorHelper
import kr.ac.kgu.app.trail.util.toast


import timber.log.Timber


@AndroidEntryPoint
class RaceMapFragment : BaseFragment<RaceMapViewModel, DataState<SaveCourseInfo>>(
    R.layout.fragment_race_map,
    RaceMapViewModel::class.java
), SensorEventListener, OnMapReadyCallback {


    private lateinit var sensorHelper: SensorHelper
    private val binding by viewBinding(FragmentRaceMapBinding::bind)
    private lateinit var locationManager: LocationManager
    private lateinit var map: GoogleMap
    private var stepCounter = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkLocationService()) {
            permissionCheck()
        }
        initUi()
        initListener()
        initSensor()
        subscribeToObservers()


    }


    private fun initSensor() {
        sensorHelper = SensorHelper(requireContext(), Constants.TYPE_STEP_DETECTOR, this)
        sensorHelper.registerListener()
        binding.stepDetectorText.text = stepCounter.toString()
    }


    private fun initListener() {
        binding.raceMapTraceBtn.setOnClickListener {

        }
    }

    private fun initUi() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.race_map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }


    private fun subscribeToObservers() {
        viewModel.getCourseDetailLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Error -> {
                    binding.progressBar.isVisible = false
                    this.toast(resources.getString(R.string.sign_up_error_toast_message_text))
                }
                is DataState.Success -> {
                    binding.progressBar.isVisible = false
                    addCoursePolyLine(result.data.courseCoordinateList)
                    addFacilityMarker(result.data.facilityList)
                }
                DataState.Loading -> binding.progressBar.isVisible = true
            }
        }

        viewModel.saveCourseLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Error -> {
                    binding.progressBar.isVisible = false
                    this.toast(resources.getString(R.string.sign_up_error_toast_message_text))
                }
                is DataState.Success -> {
                    binding.progressBar.isVisible = false
                    navigateMain()
                }
                DataState.Loading -> binding.progressBar.isVisible = true
            }
        }

    }

    override fun updateUi(model: DataState<SaveCourseInfo>) {
        when (model) {
            is DataState.Success -> {
                binding.progressBar.isVisible = false
                viewModel.getCourseDetail()
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


    private fun navigateMain() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

    }

    private fun addCoursePolyLine(courseCoordinateList: List<Coordinate>) {

        map.addPolyline(
            PolylineOptions()
                .clickable(false)
                .color(resources.getColor(R.color.course_route_color))
                .width(20f)
                .addAll(courseCoordinateList.map {
                    Timber.i("coordinate : ${it.y.toString()} ${it.x.toString()}")
                    LatLng(it.y, it.x)
                })
        )
        map.animateCamera(
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    courseCoordinateList.first().y,
                    courseCoordinateList.first().x
                ), 18f
            )
        )

    }

//    private fun addFacilityMarker(facilityList: List<Facility>) {
//        facilityList.map {
//            Timber.i(
//                "facility coordinate : ${it.coordinate.y.toString()} ${it.coordinate.x.toString()}"
//            )
//            map.addMarker(
//                MarkerOptions().position(LatLng(it.coordinate.y, it.coordinate.x))
//                    .title(it.facilityName)
//            )
//        }
//
//    }

    private fun addFacilityMarker(facilityList: List<Facility>) {
        facilityList.map {
            Timber.i(
                "facility coordinate : ${it.coordinate.y.toString()} ${it.coordinate.x.toString()}"
            )
            map.addPolyline(
                PolylineOptions()
                    .clickable(false)
                    .color(when(it.type){
                        FacilityType.OBSTACLE-> resources.getColor(R.color.course_obstacle_color)
                        FacilityType.TOILET-> resources.getColor(R.color.course_toilet_color)
                        FacilityType.CHARGE-> resources.getColor(R.color.course_charge_color)
                        FacilityType.STAIR-> resources.getColor(R.color.course_stair_color)
                        else-> resources.getColor(R.color.course_slope_color)
                    })
                    .width(20f)
                    .add(LatLng(it.coordinate.y, it.coordinate.x))
                    .add(LatLng(it.coordinate.y+0.00000000000001, it.coordinate.x))
            )
        }

    }



    private fun checkLocationService(): Boolean {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun permissionCheck() {
        val preference = requireActivity().getPreferences(MODE_PRIVATE)
        val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 없는 상태
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    ACCESS_FINE_LOCATION
                )
            ) {
                // 권한 거절
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
                builder.setPositiveButton("확인") { dialog, which ->
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE
                    )
                }
                builder.setNegativeButton("취소") { dialog, which ->

                }
                builder.show()
            } else {
                if (isFirstCheck) {
                    // 최초 권한 요청
                    preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE
                    )
                } else {
                    val builder = AlertDialog.Builder(requireActivity())
                    builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
                    builder.setPositiveButton("설정으로 이동") { dialog, which ->
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:kr.ac.kgu.app.trail")
                        )
                        startActivity(intent)
                    }
                    builder.setNegativeButton("취소") { dialog, which ->

                    }
                    builder.show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.toast("위치 권한이 승인되었습니다")

            } else {
                this.toast("위치 권한이 거절되었습니다")

            }
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        stepCounter += 1
        binding.stepDetectorText.text = stepCounter.toString()
        Timber.i("counter : $stepCounter")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}




