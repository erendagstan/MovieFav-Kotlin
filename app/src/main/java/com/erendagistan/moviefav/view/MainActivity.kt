package com.erendagistan.moviefav.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.erendagistan.moviefav.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun onSupportNavigateUp(): Boolean {
        navController = this.findNavController(R.id.fragment)
        return navController.navigateUp()
    }
}