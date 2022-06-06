package kr.ac.kgu.app.trail.ui.history

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.HistoryEntry
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.CourseRepository
import kr.ac.kgu.app.trail.repository.HistoryRepository
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel<DataState<List<HistoryEntry>>>() {

    override fun fetchInitialData() {
        getHistoryEntries()
    }

    private fun getHistoryEntries() {
        viewModelScope.launch(dispatcherProvider.io) {
            historyRepository.getHistoryList().collect {
                modelLiveData.postValue(it)
            }
        }
    }
}