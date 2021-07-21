package com.example.apiandroidtask.DI

import com.example.apiandroidtask.network.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    @Named("COINCAP_API")
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.coincap.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(@Named("COINCAP_API") retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)
}