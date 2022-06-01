package kr.ac.kgu.app.trail.ui.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.repository.KaKaoRepository
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val kaKaoRepository: KaKaoRepository,
    private val dispatcherProvider: DispatcherProvider
): ViewModel(){

    private val _checkLoginLiveData = MutableLiveData<DataState<Boolean>>()
    val checkLoginLiveData: LiveData<DataState<Boolean>> = _checkLoginLiveData

    private val _signInLiveData = MutableLiveData<DataState<Unit>>()
    val signInLiveData: LiveData<DataState<Unit>> = _signInLiveData

    init {
        checkLogin()
    }

    private fun checkLogin(){
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.checkLogin().collect {
                _checkLoginLiveData.postValue(it)
            }
        }
    }

    fun signIn() {
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.signIn().collect {
                _signInLiveData.postValue(it)
            }
        }
    }
}