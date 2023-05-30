package com.erendagistan.moviefav.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.erendagistan.moviefav.R
import com.erendagistan.moviefav.databinding.ActivityMovieDetailsBinding
import com.erendagistan.moviefav.model.Movies
import com.erendagistan.moviefav.roomdb.MovieDao
import com.erendagistan.moviefav.roomdb.MovieDatabase
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetails : AppCompatActivity() {
    private lateinit var movieDao : MovieDao
    private lateinit var movieDatabase : MovieDatabase
    private lateinit var binding : ActivityMovieDetailsBinding
    private var mDisposable : CompositeDisposable?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        movieDatabase= Room.databaseBuilder(this,MovieDatabase::class.java,"Movies").build()
        movieDao=movieDatabase.movieDao()
        mDisposable= CompositeDisposable()
        val intent = intent


        val selectedMovie = intent.getSerializableExtra("movie") as Movies
//
//        val movie_title=intent.getStringExtra("movie_title")
//        val movie_released=intent.getStringExtra("movie_released")
         val movie_image=intent.getStringExtra("movie_image")
//        val movie_duration=intent.getStringExtra("movie_duration")
//        val movie_director=intent.getStringExtra("movie_director")
//        val movie_about=intent.getStringExtra("movie_about")
//        val movie_imdb=intent.getStringExtra("movie_imdb")

        val image = Picasso.get().load(movie_image).into(binding.movieImage)
        binding.tvMovietitle.text=selectedMovie.title
        binding.tvAboutText.text=selectedMovie.about
        binding.tvYearText.text=selectedMovie.released
        binding.tvDirectorText.text=selectedMovie.director
        binding.tvDurationText.text=selectedMovie.runtime
        binding.tvImdbText.text=selectedMovie.imdb_point


        binding.btnFav.setOnClickListener {
            mDisposable?.add(movieDao.insert(selectedMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())

            Toast.makeText(this,"Favorited : ${selectedMovie.title}",Toast.LENGTH_LONG).show()

            //DELETE ROWS FROM DATABASE!!
        }
    }



}


