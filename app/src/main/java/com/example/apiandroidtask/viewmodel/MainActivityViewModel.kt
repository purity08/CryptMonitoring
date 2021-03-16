package com.example.apiandroidtask.viewmodel

import androidx.lifecycle.ViewModel
import com.example.apiandroidtask.repository.DataInterfaceImpl


class MainActivityViewModel : ViewModel() {
    val recyclerListData by lazy {
        DataInterfaceImpl().getData()
    }
    val recycler24hListData by lazy {
        DataInterfaceImpl().get24hData()
    }
}