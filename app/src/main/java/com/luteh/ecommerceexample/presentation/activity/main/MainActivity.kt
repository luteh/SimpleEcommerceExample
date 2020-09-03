package com.luteh.ecommerceexample.presentation.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.luteh.ecommerceexample.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()


    }

    fun setupViews() {
        var navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frag_main_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(btmnav_main, navHostFragment.navController)

//        var appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
        var appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.feedFragment,
                R.id.cartFragment,
                R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }
}