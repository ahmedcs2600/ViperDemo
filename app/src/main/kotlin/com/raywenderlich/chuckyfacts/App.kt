package com.raywenderlich.chuckyfacts

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    companion object{
        lateinit var INSTANCE : App
    }

    init {
        INSTANCE = this
    }

    // Routing layer (VIPER)
    lateinit var cicerone : Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        this.initCicerone()
    }

    private fun initCicerone() {
        this.cicerone = Cicerone.create()
    }
}