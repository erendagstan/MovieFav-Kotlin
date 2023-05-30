package com.erendagistan.moviefav.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.erendagistan.moviefav.R
import com.erendagistan.moviefav.model.Movies
import kotlinx.android.synthetic.main.recycler_row.view.*

class MovieAdapter (private val movieList : ArrayList<Movies>, private val listener: Listener): RecyclerView.Adapter <MovieAdapter.MovieHolder>() {

    interface Listener{
        fun onItemClick(movies: Movies)
    }


    class MovieHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(movies: Movies,position: Int,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(movies)
            }
            itemView.text_movie_title.text = movies.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position],position,listener)
    }
}