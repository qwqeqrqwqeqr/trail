package kr.ac.kgu.app.trail.ui.launch

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.ActivitySplashBinding
import kr.ac.kgu.app.trail.ui.login.LoginActivity
import kr.ac.kgu.app.trail.ui.main.MainActivity
import kr.ac.kgu.app.trail.util.Constants.SPLASH_DELAY
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.toast
import kr.ac.kgu.app.trail.util.viewBinding

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)
    private val viewModel: SplashViewModel by viewModels()
    private var handler: Handler = Handler()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.checkLoginLiveData.observe(this) { result ->
            when (result) {
                is DataState.Error -> {
                    this.toast(resources.getString(R.string.sign_up_error_toast_message_text))
                }
                is DataState.Success -> {
                    if (result.data) {
                        viewModel.signIn()
                    } else {
                        navigateToLogin()
                    }
                }
            }
        }
        viewModel.signInLiveData.observe(this) { result ->
            when (result) {
                is DataState.Error -> {
                    this.toast(resources.getString(R.string.sign_up_error_toast_message_text))
                }
                is DataState.Success -> { navigateToMain() }
            }
        }
    }
    private fun navigateToLogin(){
        handler.postDelayed({
            Intent(this, LoginActivity::class.java).also {
                startActivity(it.addFlags(FLAG_ACTIVITY_NEW_TASK))
                finish()
            }
        }, SPLASH_DELAY)
    }

    private fun navigateToMain(){
        handler.postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it.addFlags(FLAG_ACTIVITY_NEW_TASK))
                finish()
            }
        }, SPLASH_DELAY)
    }
}