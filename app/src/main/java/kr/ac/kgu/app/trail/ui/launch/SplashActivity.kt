package kr.ac.kgu.app.trail.ui.launch

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.databinding.ActivitySplashBinding
import kr.ac.kgu.app.trail.ui.auth.LoginActivity
import kr.ac.kgu.app.trail.util.Constants.SPLASH_DELAY
import kr.ac.kgu.app.trail.util.viewBinding

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
            Intent(this, LoginActivity::class.java).also {
                startActivity(it.addFlags(FLAG_ACTIVITY_NEW_TASK))
                finish()
            }
        }, SPLASH_DELAY)
    }
}