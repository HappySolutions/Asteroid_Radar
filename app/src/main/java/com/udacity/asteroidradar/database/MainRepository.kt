package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.await

class MainRepository(private val database: AsteroidsDataBase) : DataSource {
    override suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val response = RetrofitBuilder.retrofitService.getAstroids().await()
            database.asteroidsDatabaseDao.insertAll(*response  as Array<out Asteroid>)
        }
    }

    override suspend fun getAllAsteroids(): Flow<List<Asteroid>> {
        return database.asteroidsDatabaseDao.getAllAstroids()
    }


}
