package kr.ac.kgu.app.trail.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcherProvider: DispatcherProvider
    ): ViewModel() {


    private val _authUserLiveData = MutableLiveData<DataState<Unit>>()
    val authUserLiveData: LiveData<DataState<Unit>> = _authUserLiveData

    fun signUp() {
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.signUp().collect {
                _authUserLiveData.postValue(it)
            }
        }
    }

}