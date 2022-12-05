package com.udacity.asteroidradar.database

import com.udacity.asteroidradar.Asteroid
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun refreshData()
    //can I use suspend with flow
    suspend fun getAllAsteroids(): Flow<List<Asteroid>>
}