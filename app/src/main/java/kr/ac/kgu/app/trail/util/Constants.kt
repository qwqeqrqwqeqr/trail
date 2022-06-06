package kr.ac.kgu.app.trail.util

import android.Manifest
import android.hardware.Sensor

object Constants {

    //Time
    const val DEFAULT_TIMEOUT = 10000
    const val SPLASH_DELAY = 2000L

    //Key
    const val NATIVE_APP_KEY = "6af5df9a58183ca0b1ae885d99189244"

    //
    const val BASE_URL = "http://3.39.97.38/"
    const val BEARER = "Bearer "

    //Sensor
    const val TYPE_STEP_DETECTOR = Sensor.TYPE_STEP_COUNTER

    //Permission
    const val LOCATION_PERMISSION_REQUEST_CODE = 100
    var REQUIRED_PERMISSIONS = arrayOf<String>( Manifest.permission.ACCESS_FINE_LOCATION)

}