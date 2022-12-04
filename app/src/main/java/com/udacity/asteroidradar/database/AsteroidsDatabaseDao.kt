package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidsDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(vararg manyAsteroids: Asteroid)

    @Query("SELECT * FROM asteroids_table")
     fun getAllAsteroids(): Flow<List<Asteroid>>
}