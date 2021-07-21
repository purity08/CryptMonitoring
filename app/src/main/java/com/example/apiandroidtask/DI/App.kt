package com.example.apiandroidtask.DI

import android.app.Application

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }
    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .restModule(RestModule())
            .build()
    }
}