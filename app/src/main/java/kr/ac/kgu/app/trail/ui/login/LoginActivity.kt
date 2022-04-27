package kr.ac.kgu.app.trail.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kgu.app.trail.databinding.ActivityLoginBinding
import kr.ac.kgu.app.trail.util.viewBinding

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}