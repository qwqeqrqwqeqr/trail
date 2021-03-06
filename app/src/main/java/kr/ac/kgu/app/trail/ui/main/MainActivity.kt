package kr.ac.kgu.app.trail.ui.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.ActivityMainBinding
import kr.ac.kgu.app.trail.ui.account.AccountFragment
import kr.ac.kgu.app.trail.ui.course.CourseFragment
import kr.ac.kgu.app.trail.ui.history.HistoryFragment
import kr.ac.kgu.app.trail.ui.home.HomeFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        binding.mainBottomNavigation.selectedItemId = R.id.main_menu_home_item

    }



    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        binding.mainBottomNavigation.setupWithNavController(navController)

        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container_view, homeFragment).commit()

    }
    private val onNavigationItemSelectedListener = BottomNavigationView
        .OnNavigationItemSelectedListener{
            changeFragment(
            when(it.itemId){
                R.id.main_menu_home_item -> { HomeFragment() }
                R.id.main_menu_course_item -> { CourseFragment() }
                R.id.main_menu_history_item -> { HistoryFragment() }
                else -> { AccountFragment() }
            })
            true
        }


    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_container_view, fragment)
            .commit()
    }
}