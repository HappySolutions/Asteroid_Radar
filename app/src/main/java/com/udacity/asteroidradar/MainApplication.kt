package com.udacity.asteroidradar

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner.get
import com.udacity.asteroidradar.database.AsteroidsDataBase
import com.udacity.asteroidradar.database.DataSource
import com.udacity.asteroidradar.database.MainRepository
import com.udacity.asteroidradar.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        val myModule = module {

            viewModel {
                MainViewModel(
                    get() as DataSource,
                    get()
                )
            }

            single { MainRepository(get()) as DataSource}
            single { AsteroidsDataBase.getInstance(this@MainApplication) }
        }

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(myModule))
        }
    }
    }

