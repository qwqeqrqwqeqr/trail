package kr.ac.kgu.app.trail.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.CourseRepository
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel<DataState<List<CourseEntry>>>() {


    private val _saveTempCourseLiveData = MutableLiveData<DataState<Unit>>()
    val saveTempCourseLiveData: LiveData<DataState<Unit>> = _saveTempCourseLiveData

    override fun fetchInitialData() {
        getCourseEntries()
    }



    private fun getCourseEntries() {
        viewModelScope.launch(dispatcherProvider.io) {
            courseRepository.getCourseList().collect {
                modelLiveData.postValue(it)
            }
        }
    }


    fun saveTempCourse(courseEntry: CourseEntry) {
        viewModelScope.launch(dispatcherProvider.io) {
            courseRepository.saveTempCourse(courseEntry).collect {
                _saveTempCourseLiveData.postValue(it)
            }
        }
    }


}