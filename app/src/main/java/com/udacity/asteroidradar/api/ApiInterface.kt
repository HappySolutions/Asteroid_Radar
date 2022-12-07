package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.Constants.END_DATE
import com.udacity.asteroidradar.Constants.START_DATE
import com.udacity.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/neo/rest/v1/feed")
      fun getAsteroids(@Query("start_date") startDate: String = getToday(),
                    @Query("end_date") endDate: String = getSeventhDay(),
                    @Query("api_key") apiKey: String = API_KEY
    ): Deferred<ResponseBody>

    @GET("/planetary/apod")
    suspend fun getNasaImage(@Query("api_key") apiKey: String = API_KEY): PictureOfDay

}