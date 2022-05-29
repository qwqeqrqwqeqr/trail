package kr.ac.kgu.app.trail.ui.race

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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


    private fun loadTempCourse() {
        viewModelScope.launch(dispatcherProvider.io) {
            courseRepository.loadTempCourse().collect {
                modelLiveData.postValue(it)
            }
        }
    }

    //TODO model live data type을 좌표정보로 바꿔야함
}