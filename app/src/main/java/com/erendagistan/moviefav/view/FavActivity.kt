package com.erendagistan.moviefav.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erendagistan.moviefav.R
import com.erendagistan.moviefav.databinding.ActivityFavBinding

class FavActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}