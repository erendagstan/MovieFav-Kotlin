package com.erendagistan.moviefav.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.erendagistan.moviefav.model.ImageTypeConverter
import com.erendagistan.moviefav.model.Movies

@Database(entities = [Movies::class], version = 1)
@TypeConverters(ImageTypeConverter::class)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}