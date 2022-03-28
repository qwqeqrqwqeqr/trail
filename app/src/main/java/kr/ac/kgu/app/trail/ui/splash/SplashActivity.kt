package kr.ac.kgu.app.trail.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.MainActivity
import kr.ac.kgu.app.trail.databinding.ActivitySplashBinding
import kr.ac.kgu.app.trail.util.Constants.SPLASH_DELAY
import kr.ac.kgu.app.trail.util.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)
    private val viewModel: SplashViewModel by viewModels()
    private var handler: Handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navigateToLogin()
    }


    private fun navigateToLogin(){
        handler.postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, SPLASH_DELAY)
    }
}