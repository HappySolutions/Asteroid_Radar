package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Asteroid

@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
abstract class AsteroidsDataBase : RoomDatabase() {
    abstract val asteroidsDatabaseDao: AsteroidsDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: AsteroidsDataBase? = null
        fun getInstance(context: Context): AsteroidsDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidsDataBase::class.java,
                        "asteroids_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
    }