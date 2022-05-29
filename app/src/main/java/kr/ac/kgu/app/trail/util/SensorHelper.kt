package kr.ac.kgu.app.trail.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorHelper(private val context: Context, private val sensorType: Int, private val eventListener: SensorEventListener) {


    fun getSensorManager()  =context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    fun getSensor(): Sensor = getSensorManager().getDefaultSensor(sensorType)


    fun registerListener() {
        getSensorManager().registerListener(eventListener ,getSensor(), SensorManager.SENSOR_DELAY_NORMAL)
    }
    fun unRegisterListener(){
        getSensorManager().unregisterListener(eventListener, getSensor() )
    }

}