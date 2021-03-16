package com.example.apiandroidtask.singleton

import com.example.apiandroidtask.ONE_DAY_IN_MILS

object Singleton {
    var id: String = ""
    var name: String = ""
    var symbol: String = ""
    val end = System.currentTimeMillis()
    val start = end - ONE_DAY_IN_MILS
}