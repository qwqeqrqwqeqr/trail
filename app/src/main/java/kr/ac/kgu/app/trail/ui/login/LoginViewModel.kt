package kr.ac.kgu.app.trail.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.repository.AuthRepository
import javax.inject.Inject


@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcherProvider: DispatcherProvider
    ): ViewModel() {




}