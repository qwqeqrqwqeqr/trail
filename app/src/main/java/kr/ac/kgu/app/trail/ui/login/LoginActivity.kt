package kr.ac.kgu.app.trail.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.databinding.ActivityLoginBinding
import kr.ac.kgu.app.trail.databinding.ActivityLoginBinding.inflate
import kr.ac.kgu.app.trail.util.viewBinding


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

}