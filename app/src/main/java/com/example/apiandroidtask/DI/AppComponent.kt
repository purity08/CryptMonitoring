package com.example.apiandroidtask.DI

import com.example.apiandroidtask.AdditionalInfoActivity
import com.example.apiandroidtask.MainActivity
import com.example.apiandroidtask.repository.DataInterfaceImpl
import com.example.apiandroidtask.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RestModule::class, AppModule::class, MainActivityViewModel::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(additionalInfoActivity: AdditionalInfoActivity)
    fun inject(dataInterface: DataInterfaceImpl)
}