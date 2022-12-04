package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.models.PictureOfDay
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/neo/rest/v1/feed")
    fun getAstroids(@Query("start_date") startDate: String = API_KEY,
                    @Query("end_date") endDate: String = Constants.START_DATE,
                    @Query("api_key") apiKey: String = Constants.END_DATE
    ): Call<List<Asteroid>>

    @GET("/planetary/apod")
    fun getNasaImage(@Query("api_key") apiKey: String = API_KEY): Call<PictureOfDay>

}