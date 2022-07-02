package com.example.authentification_cloud

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.authentification_cloud.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response

lateinit var MainActivityView : Activity
var db : ReservationDao? = null
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        MainActivityView = this
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        thisView = view

        val navFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navFragment.navController
        NavigationUI.setupWithNavController(binding.navBottom,navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

}