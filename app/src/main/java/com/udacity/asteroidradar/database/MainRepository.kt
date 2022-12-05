package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.RetrofitBuilder
//import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import retrofit2.await

class MainRepository(private val database: AsteroidsDataBase) : DataSource {
    override suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val response = RetrofitBuilder.retrofitService.getAsteroids()
            // I changed *response.results.asDatabaseModel() to *response.results as ArrayList<Asteroid> but give error
            //مش مفروض هنا نستخدم الفانكشن اللى هما ادوهالنا
            database.asteroidsDatabaseDao.insertAll(*response as Array<Asteroid>)
        }
    }

    override suspend fun getAllAsteroids(): Flow<List<Asteroid>> {
        return database.asteroidsDatabaseDao.getAllAsteroids()
    }


}
