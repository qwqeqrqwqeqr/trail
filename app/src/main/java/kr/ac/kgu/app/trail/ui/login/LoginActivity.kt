package kr.ac.kgu.app.trail.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.ui.main.MainActivity
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.ActivityLoginBinding
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.toast
import kr.ac.kgu.app.trail.util.viewBinding


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        subscribeToObservers()

        binding.loginKakaoLoginBtn.setOnClickListener {
            viewModel.SignUp()
        }
    }


    private fun subscribeToObservers() {
        viewModel.authUserLiveData.observe(this) { result ->
            when (result) {
                is DataState.Error -> {
                    binding.progressBar.isVisible = false
                    this.toast(resources.getString(R.string.sign_up_error_toast_message_text))
                }
                is DataState.Success -> {
                    binding.progressBar.isVisible = false
                    navigateMainScreen()
                }
                DataState.Loading -> binding.progressBar.isVisible = true
            }
        }

    }

    private fun navigateMainScreen() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

}