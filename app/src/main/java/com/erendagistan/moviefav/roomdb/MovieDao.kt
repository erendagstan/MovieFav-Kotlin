package com.erendagistan.moviefav.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.erendagistan.moviefav.model.Movies
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("DELETE From Movies")
    fun deleteAll()

    @Insert
    fun insert(movies: Movies) : Completable

    @Delete
    fun delete(movies:List<Movies>) : Completable
}