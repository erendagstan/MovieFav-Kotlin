package com.erendagistan.moviefav.service

import com.erendagistan.moviefav.model.Movies
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieAPI {
    @GET("saniyusuf/406b843afdfb9c6a86e25753fe2761f4/raw/523c324c7fcc36efab8224f9ebb7556c09b69a14/Film.JSON")
    fun getData() : Observable<List<Movies>>
}