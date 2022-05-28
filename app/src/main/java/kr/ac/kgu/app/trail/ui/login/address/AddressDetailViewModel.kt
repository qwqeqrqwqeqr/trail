package kr.ac.kgu.app.trail.ui.login.address

import kr.ac.kgu.app.trail.data.model.AddressEntry
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.ui.base.BaseViewModel
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

class AddressDetailViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<DataState<List<AddressEntry>>>() {
    override fun fetchInitialData() {

    }
}