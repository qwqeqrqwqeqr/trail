package kr.ac.kgu.app.trail.ui.race

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kr.ac.kgu.app.trail.data.model.CourseDetail
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.SaveCourseInfo
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.CourseRepository
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import kr.ac.kgu.app.trail.util.Constants.TIMER_MINUTE_DELAY

import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.SingleLiveEvent
import kr.ac.kgu.app.trail.util.parser.getCurrentDate
import kr.ac.kgu.app.trail.util.parser.parseDate
import timber.log.Timber
import timber.log.Timber.Forest.i
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RaceMapViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel<DataState<SaveCourseInfo>>(){
    override fun fetchInitialData() {
        loadTempCourse()
        _stepCountLiveData.value =0
    }

    private lateinit var timer : Job
    private lateinit var saveCourseInfo : SaveCourseInfo

    private val _getCourseDetailLiveData = MutableLiveData<DataState<CourseDetail>>()
    val getCourseDetailLiveData: LiveData<DataState<CourseDetail>> = _getCourseDetailLiveData

    private val _saveCourseLiveData = MutableLiveData<DataState<Unit>>()
    val saveCourseLiveData: LiveData<DataState<Unit>> = _saveCourseLiveData



    private var workStartTime = ""
    private var workFinishTime = ""

    private val _workTimeLiveData = MutableLiveData<Int>()
    val workTimeLiveData: LiveData<Int> = _workTimeLiveData

    private val _stepCountLiveData = MutableLiveData<Int>()
    val stepCountLiveData: LiveData<Int> = _stepCountLiveData


    fun setSaveCourseInfo(saveCourseInfo: SaveCourseInfo) {this.saveCourseInfo = saveCourseInfo}


    fun getCourseDetail(){
        viewModelScope.launch(dispatcherProvider.default) {
            courseRepository.getCourseDetail().collect {
                _getCourseDetailLiveData.postValue(it)
            }
        }
    }

    private fun runTimer(){
        timer = viewModelScope.launch(dispatcherProvider.main) {
            var count:Int = 0
            while (true) {
                delay(TIMER_MINUTE_DELAY)
                count++
                _workTimeLiveData.value= count
                Timber.i("run course Timer : $count")
            }
        }
    }

    fun stopTimer(){ timer.cancel() }

    fun addStepCount(){
        _stepCountLiveData.value = _stepCountLiveData.value?.plus(1)
        Timber.i("run stepCounter : ${_stepCountLiveData.value}")
    }


    fun saveCourse(){
        saveCourseInfo.workTime = _workTimeLiveData.value ?: 0
        saveCourseInfo.stepCount = _stepCountLiveData.value ?: 0
        saveCourseInfo.workStartTime = workStartTime
        saveCourseInfo.workFinishTime = parseDate(getCurrentDate())

        with(Timber) {
            i("saveCourse distance : ${saveCourseInfo!!.distance}")
            i("saveCourse workFinishTime : ${saveCourseInfo!!.workFinishTime}")
            i("saveCourse workStartTime : ${saveCourseInfo!!.workStartTime}")
            i("saveCourse workTime : ${saveCourseInfo!!.workTime}")
            i("saveCourse stepCount : ${saveCourseInfo!!.stepCount}")
            i("saveCourse courseAddress : ${saveCourseInfo!!.courseAddress}")
            i("saveCourse courseName : ${saveCourseInfo!!.courseName}")
        }

        viewModelScope.launch(dispatcherProvider.default) {
            courseRepository.saveCourse(saveCourseInfo!!).collect{
                _saveCourseLiveData.postValue(it)
            }
        }

    }

    fun startRace(){
        viewModelScope.launch(dispatcherProvider.default) {
            runTimer()
            workStartTime = parseDate(getCurrentDate())
        }
    }


    private fun loadTempCourse() {
        viewModelScope.launch(dispatcherProvider.default) {
            courseRepository.loadTempCourse().collect {
                modelLiveData.postValue(it)
            }
        }
    }

}