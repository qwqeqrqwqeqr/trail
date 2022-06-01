package kr.ac.kgu.app.trail.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.data.model.UserInfo
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.UserRepository
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel<DataState<UserInfo>>() {



    override fun fetchInitialData() {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch(dispatcherProvider.io) {
            userRepository.getUserInfo().collect {
                modelLiveData.postValue(it)
            }
        }
    }
}