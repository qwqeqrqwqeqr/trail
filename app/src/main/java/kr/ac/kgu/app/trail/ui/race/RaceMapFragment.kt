package kr.ac.kgu.app.trail.ui.race

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.media.audiofx.BassBoost
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.databinding.FragmentRaceMapBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.viewBinding
import net.daum.mf.map.api.MapView



@AndroidEntryPoint
class RaceMapFragment : BaseFragment<RaceMapViewModel, CourseEntry>(
    R.layout.fragment_race_map,
    RaceMapViewModel::class.java
) {

    private lateinit var mapView : MapView
    private val binding by viewBinding(FragmentRaceMapBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initListener()

    }

    private fun initListener() {
        binding.raceMapTraceBtn.setOnClickListener { startTracking() }
    }

    private fun initUi(){
        mapView = MapView(requireActivity())
        binding.raceMapView.addView(mapView)

    }

    override fun updateUi(model: CourseEntry) {

    }

    private fun startTracking() {
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }

    // 위치추적 중지
    private fun stopTracking() {
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
    }





}