package kr.ac.kgu.app.trail.ui.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.ac.kgu.app.trail.repository.TrailRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    trailRepository: TrailRepository
): ViewModel(){



}