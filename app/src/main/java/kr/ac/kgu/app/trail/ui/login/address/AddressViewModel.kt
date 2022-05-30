package kr.ac.kgu.app.trail.ui.login.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.repository.UserRepository
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val dispatcherProvider: DispatcherProvider
): ViewModel(){

    private val _selectedAddressLiveData = MutableLiveData<String>()
    val selectedAddressLiveData: LiveData<String> = _selectedAddressLiveData

    private val _signInLiveData = MutableLiveData<DataState<Unit>>()
    val signInLiveData: LiveData<DataState<Unit>> = _signInLiveData

    private val _signUpLiveData = MutableLiveData<DataState<Unit>>()
    val signUpLiveData: LiveData<DataState<Unit>> = _signUpLiveData

    private val _getAddressListLiveData = MutableLiveData<DataState<List<String>>>()
    val getAddressListLiveData: LiveData<DataState<List<String>>> = _getAddressListLiveData

    init { _selectedAddressLiveData.postValue("") }

    fun selectAddress(item:String){
        _selectedAddressLiveData.postValue(item)
    }

    fun signUp() {
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.signUp().collect {
                _signUpLiveData.postValue(it)
            }
        }
    }

    fun getAddressList(){
        viewModelScope.launch(dispatcherProvider.io) {
            userRepository.getAddressList().collect {
                _getAddressListLiveData.postValue(it)
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