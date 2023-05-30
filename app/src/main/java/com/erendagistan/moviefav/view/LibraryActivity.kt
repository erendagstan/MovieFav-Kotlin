package com.erendagistan.moviefav.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.erendagistan.moviefav.adapter.MovieAdapter
import com.erendagistan.moviefav.databinding.ActivityLibraryBinding
import com.erendagistan.moviefav.model.Movies
import com.erendagistan.moviefav.roomdb.MovieDao
import com.erendagistan.moviefav.roomdb.MovieDatabase
import com.erendagistan.moviefav.service.MovieAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_library.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LibraryActivity : AppCompatActivity() , MovieAdapter.Listener {
    private lateinit var movieDao : MovieDao
    private lateinit var movieDatabase : MovieDatabase
    private val BASE_URL = "https://gist.githubusercontent.com/"
    private var movieModels : ArrayList<Movies>?=null
    private var movie_adapter : MovieAdapter?=null
    //Disposable
    private var compositeDisposable : CompositeDisposable?=null
    private var mDisposable : CompositeDisposable?=null
    private lateinit var binding : ActivityLibraryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLibraryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        movieDatabase= Room.databaseBuilder(this,MovieDatabase::class.java,"Movies").build()
        movieDao=movieDatabase.movieDao()
        mDisposable=CompositeDisposable()
        compositeDisposable= CompositeDisposable()

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        loadData()


        binding.btnFavList.setOnClickListener {
            val intent = Intent(this,FavActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MovieAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))


    }

    private fun handleResponse(movieList: List<Movies>){
        movieModels= ArrayList(movieList)
        movieModels?.let {
            movie_adapter=MovieAdapter(it,this@LibraryActivity)
            recyclerView.adapter=movie_adapter
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onItemClick(movies: Movies) {
        val intent = Intent(this@LibraryActivity,MovieDetails::class.java)
//        intent.putExtra("movie_title",movies.title)
          intent.putExtra("movie_image",movies.image[0])
//        intent.putExtra("movie_duration",movies.runtime)
//        intent.putExtra("movie_director",movies.director)
//        intent.putExtra("movie_about",movies.about)
//        intent.putExtra("movie_released",movies.released)
//        intent.putExtra("movie_imdb",movies.imdb_point)
        intent.putExtra("movie",movies)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }


}