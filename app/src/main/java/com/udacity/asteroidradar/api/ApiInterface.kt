package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // I wanted to change Deferred To Flow but gives error on the await() on MainRepository
    // when change to ArrayList the await() on MainRepository gives error
    //I want to this function to return Asteroid so tha on MainRepository not call await() and on the second function
    //I pass the response as it is since fun insertAll(vararg manyAsteroids: Asteroid) on Dao takes vararg
    @GET("/neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("start_date") startDate: String = API_KEY,
                    @Query("end_date") endDate: String = Constants.START_DATE,
                    @Query("api_key") apiKey: String = Constants.END_DATE
    ): Asteroid

    @GET("/planetary/apod")
    suspend fun getNasaImage(@Query("api_key") apiKey: String = API_KEY): Deferred<PictureOfDay>

}