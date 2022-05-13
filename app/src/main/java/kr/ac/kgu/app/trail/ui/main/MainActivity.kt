package kr.ac.kgu.app.trail.ui.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.ActivityMainBinding
import kr.ac.kgu.app.trail.ui.account.AccountFragment
import kr.ac.kgu.app.trail.ui.course.CourseFragment
import kr.ac.kgu.app.trail.ui.history.HistoryFragment
import kr.ac.kgu.app.trail.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

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
            when(it.itemId){
                R.id.main_menu_home_item -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container_view, homeFragment).commit()
                }
                R.id.main_menu_course_item -> {
                   val courseFragment = CourseFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container_view, courseFragment).commit()
                }
                R.id.main_menu_history_item -> {
                    val historyFragment = HistoryFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container_view, historyFragment).commit()
                }
                R.id.main_menu_account_item -> {
                    val accountFragment = AccountFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container_view, accountFragment).commit()
                }
            }
            true
        }

}
