package com.example.apiandroidtask.repository

import androidx.lifecycle.LiveData
import com.example.apiandroidtask.model.RecyclerData
import com.example.apiandroidtask.model.IntervalData


interface DataInterface {
    fun getData(): LiveData<ArrayList<RecyclerData>>
    fun get24hData(): LiveData<ArrayList<IntervalData>>
}