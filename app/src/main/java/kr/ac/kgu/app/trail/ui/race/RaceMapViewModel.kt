package kr.ac.kgu.app.trail.ui.race

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.CourseRepository
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RaceMapViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel<CourseEntry>(){
    override fun fetchInitialData() {

    }
}