package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidsDatabaseDao {
    @Insert
    suspend fun insertAll(vararg manyAstroids: Asteroid)

    @Query("SELECT * FROM asteroids_table")
    suspend fun getAllAstroids(): LiveData<List<Asteroid>>
}