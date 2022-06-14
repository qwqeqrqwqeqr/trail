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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings
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
import kr.ac.kgu.app.trail.util.Constants.LOCATION_PERMISSION_REQUEST_CODE
import kr.ac.kgu.app.trail.util.Constants.REQUIRED_PERMISSIONS
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.SensorHelper
import kr.ac.kgu.app.trail.util.toast


import timber.log.Timber


@AndroidEntryPoint
class RaceMapFragment : BaseFragment<RaceMapViewModel, DataState<SaveCourseInfo>>(
    R.layout.fragment_race_map,
    RaceMapViewModel::class.java
), SensorEventListener, OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    ActivityCompat.OnRequestPermissionsResultCallback {


    private lateinit var sensorHelper: SensorHelper
    private val binding by viewBinding(FragmentRaceMapBinding::bind)
    private lateinit var locationManager: LocationManager
    private lateinit var map: GoogleMap
    private var stepCounter = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListener()
        initSensor()
        subscribeToObservers()
    }

    private fun initSensor() {
        sensorHelper = SensorHelper(requireContext(), Constants.TYPE_STEP_DETECTOR, this)
    }


    private fun initListener() {
        binding.raceMapStartBtn.setOnClickListener {
            viewModel.startRace()
            sensorHelper.registerListener()
            binding.raceMapStartBtn.visibility = View.GONE
            binding.raceMapFinishBtn.visibility = View.VISIBLE
        }
        binding.raceMapFinishBtn.setOnClickListener {
            viewModel.saveCourse()
        }
    }

    private fun initUi() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.race_map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.raceMapTraceBtn.isVisible = false
        binding.lifecycleOwner = this
        binding.vm = viewModel
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
                    addFacilityCircle(result.data.facilityList)
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
                    viewModel.stopTimer()
                    sensorHelper.unRegisterListener()
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
                viewModel.setSaveCourseInfo(model.data)
                with(Timber) {
                    i("saveCourse distance : ${model.data!!.distance}")
                    i("saveCourse workFinishTime : ${model.data!!.workFinishTime}")
                    i("saveCourse workStartTime : ${model.data!!.workStartTime}")
                    i("saveCourse workTime : ${model.data!!.workTime}")
                    i("saveCourse stepCount : ${model.data!!.stepCount}")
                    i("saveCourse courseAddress : ${model.data!!.courseAddress}")
                    i("saveCourse courseName : ${model.data!!.courseName}")
                }
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
        if (checkLocationService()) {
            permissionCheck()
            map = googleMap
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            map.isMyLocationEnabled = true
            googleMap.setOnMyLocationButtonClickListener(this)
            googleMap.setOnMyLocationClickListener(this)
        }

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


    private fun addFacilityCircle(facilityList: List<Facility>) {
        facilityList.map {
            Timber.i(
                "facility coordinate : ${it.coordinate.y.toString()} ${it.coordinate.x.toString()}"
            )
            map.addCircle(
                CircleOptions()
                    .center(LatLng(it.coordinate.y, it.coordinate.x))
                    .radius(5.0)
                    .strokeWidth(0f)
                    .fillColor(
                        when (it.type) {
                            FacilityType.OBSTACLE -> resources.getColor(R.color.course_obstacle_color)
                            FacilityType.TOILET -> resources.getColor(R.color.course_toilet_color)
                            FacilityType.CHARGE -> resources.getColor(R.color.course_charge_color)
                            FacilityType.STAIR -> resources.getColor(R.color.course_stair_color)
                            else -> resources.getColor(R.color.course_slope_color)
                        }
                    )
                    .clickable(true)
            )
            map.setOnCircleClickListener {

            }


        }

    }

    override fun onMyLocationButtonClick(): Boolean {
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(p0: Location) {}


    private fun checkLocationService(): Boolean {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }




    //Permission
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
                        REQUIRED_PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE
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
                        REQUIRED_PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE
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
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.toast("위치 권한이 승인되었습니다")

            } else {
                this.toast("위치 권한이 거절되었습니다")

            }
        }
    }




    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (sensorEvent.sensor == sensorHelper.getSensor()) {
            viewModel.addStepCount()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}




