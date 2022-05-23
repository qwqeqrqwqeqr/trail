package kr.ac.kgu.app.trail.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val authRepository: AuthRepository,

    private val dispatcherProvider: DispatcherProvider
): ViewModel(){

    private val _signInLiveData = MutableLiveData<DataState<Unit>>()
    val signInLiveData: LiveData<DataState<Unit>> = _signInLiveData

    private val _signUpLiveData = MutableLiveData<DataState<Unit>>()
    val signUpLiveData: LiveData<DataState<Unit>> = _signUpLiveData


    fun signUp() {
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.signUp().collect {
                _signUpLiveData.postValue(it)
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