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
    //want to know about vararg and why not List<Asteroid> *Look at ApiInterface
    //should I use insert to get the list of Asteroids??
     fun insertAll(vararg manyAsteroids: Asteroid)

    @Query("SELECT * FROM asteroids_table")
    //want to understand about using Flow here
     fun getAllAsteroids(): Flow<List<Asteroid>>
}