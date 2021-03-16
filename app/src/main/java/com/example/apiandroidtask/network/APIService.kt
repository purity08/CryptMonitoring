package com.example.apiandroidtask.network

import com.example.apiandroidtask.model.RecyclerList
import com.example.apiandroidtask.model.CryptData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("assets")
    fun getCryptList(): Call<RecyclerList>

    @GET("assets/{id}/history?interval=m1")
    fun get24hCryptList(
        @Path("id") id: String,
        @Query("start") start: Long,
        @Query("end") end: Long
    ): Call<CryptData>

}