package kr.ac.kgu.app.trail.ui.race

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kgu.app.trail.R

class RaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_race)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RaceFragment.newInstance())
                .commitNow()
        }
    }
}