package kr.ac.kgu.app.trail.ui.race

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.data.model.CourseDetail
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.SaveCourseInfo
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.CourseRepository
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class RaceMapViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel<DataState<SaveCourseInfo>>(){
    override fun fetchInitialData() {
        loadTempCourse()
    }

    private val _getCourseDetailLiveData = MutableLiveData<DataState<CourseDetail>>()
    val getCourseDetailLiveData: LiveData<DataState<CourseDetail>> = _getCourseDetailLiveData

    private val _saveCourseLiveData = MutableLiveData<DataState<Unit>>()
    val saveCourseLiveData: LiveData<DataState<Unit>> = _saveCourseLiveData



    fun getCourseDetail(){
        viewModelScope.launch(dispatcherProvider.default) {
            courseRepository.getCourseDetail().collect {
                _getCourseDetailLiveData.postValue(it)
            }
        }
    }

//    fun saveCourse(){
//        viewModelScope.launch(dispatcherProvider.default) {
//            courseRepository.saveCourse().collect{
//
//            }
//        }
//
//    } //TODO save course 구현하기



    private fun loadTempCourse() {
        viewModelScope.launch(dispatcherProvider.default) {
            courseRepository.loadTempCourse().collect {
                modelLiveData.postValue(it)
            }
        }
    }

}