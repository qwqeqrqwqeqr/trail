package kr.ac.kgu.app.trail.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModel<Long>() {


    override fun fetchInitialData() {

    }
}