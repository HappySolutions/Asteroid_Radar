package com.udacity.asteroidradar.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.IMAGE_MEDIA_TYPE
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.asDomainModel
import com.udacity.asteroidradar.models.PictureOfDay
//import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okhttp3.internal.wait
import org.json.JSONObject
import retrofit2.await

class MainRepository(private val database: AsteroidsDataBase) : DataSource {
    override suspend fun refreshData()
    {
//        var response: Asteroid? = null
        withContext(Dispatchers.IO) {
            val response  = RetrofitBuilder.retrofitService.getAsteroids().await()
            Log.i("MainRepository", response.toString())
            // I changed *response.results.asDatabaseModel() to *response.results as ArrayList<Asteroid> but give error
            //مش مفروض هنا نستخدم الفانكشن اللى هما ادوهالنا
            var asteroidList = parseAsteroidsJsonResult(JSONObject(response.string()))
            Log.i("MainRepository", asteroidList.size.toString())

            database.asteroidsDatabaseDao.insertAll(*asteroidList.asDomainModel())
        }
    }

    override suspend fun getAllAsteroids(): Flow<List<Asteroid>> {
        return database.asteroidsDatabaseDao.getAllAsteroids()
    }

    override suspend fun getNasaPic(): PictureOfDay? {
        var pictureOfDay: PictureOfDay? = null
        withContext(Dispatchers.IO) {
            pictureOfDay = RetrofitBuilder.retrofitService.getNasaImage()
        }
        if (pictureOfDay?.mediaType == IMAGE_MEDIA_TYPE) {
            return pictureOfDay
        }
        return null
    }

    override suspend fun onWeekAsteroidsClicked(): Flow<List<Asteroid>> {
        return database.asteroidsDatabaseDao.getAsteroidsByCloseApproachDate(getToday(), getSeventhDay())
    }
    override suspend fun onTodayAsteroidsClicked(): Flow<List<Asteroid>> {
        return database.asteroidsDatabaseDao.getAsteroidsByCloseApproachDate(getToday(), getToday())
    }override suspend fun onSavedAsteroidsClicked(): Flow<List<Asteroid>> {
        return database.asteroidsDatabaseDao.getAllAsteroids()
    }
}
