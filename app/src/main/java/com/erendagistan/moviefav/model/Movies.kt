package com.erendagistan.moviefav.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity
data class Movies(
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title : String,
    @SerializedName("Released")
    @ColumnInfo(name = "released")
    val released : String,
    @SerializedName("Runtime")
    @ColumnInfo(name = "runtime")
    val runtime: String,
    @SerializedName("Images")
    @ColumnInfo(name = "images")
    val image : List<String>,
    @SerializedName("imdbRating")
    @ColumnInfo(name = "imdb")
    val imdb_point : String,
    @SerializedName("Director")
    @ColumnInfo(name = "director")
    val director : String,
    @SerializedName("Plot")
    @ColumnInfo(name = "about")
    val about: String
) : java.io.Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id : Int =0
}

class ImageTypeConverter{
    @TypeConverter
    fun fromString(value:String): List<String>{
        val listType = object:TypeToken<List<String>>(){}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun frmList(list:List<String>) : String{
        return Gson().toJson(list)
    }

}


