package kr.ac.kgu.app.trail.ui.course

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
    override fun fetchInitialData() {
        getAllCourseEntries()
    }

    private fun getAllCourseEntries() {
        viewModelScope.launch(dispatcherProvider.io) {
            courseRepository.getAllCourseList().collect {
                modelLiveData.postValue(it)
            }
        }
    }
}