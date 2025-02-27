package com.example.todo_list.todo_list.data.data_source

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBoxManager.init(this)
    }
}