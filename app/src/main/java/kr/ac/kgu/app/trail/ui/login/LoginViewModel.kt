package kr.ac.kgu.app.trail.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

class LoginViewModel  @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcherProvider: DispatcherProvider
    ): ViewModel() {


    private val _authUserLiveData = MutableLiveData<DataState<Unit>>()
    val authUserLiveData: LiveData<DataState<Unit>> = _authUserLiveData

    fun SignUp() {
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.signUp().collect {
                _authUserLiveData.postValue(it)
            }
        }
    }

}