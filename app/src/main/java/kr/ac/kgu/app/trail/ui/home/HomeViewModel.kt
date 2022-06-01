package kr.ac.kgu.app.trail.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.ac.kgu.app.trail.data.model.UserInfo
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModel<DataState<UserInfo>>() {


    override fun fetchInitialData() {

    }
}