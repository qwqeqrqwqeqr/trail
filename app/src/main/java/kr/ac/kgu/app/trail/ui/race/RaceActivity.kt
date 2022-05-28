package kr.ac.kgu.app.trail.ui.race

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.ActivityRaceBinding
import kr.ac.kgu.app.trail.util.viewBinding

@AndroidEntryPoint
class RaceActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityRaceBinding::inflate)
    private val viewModel: RaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}