package com.example.todo_list.todo_list.data.data_source

import android.app.Application
import android.util.Log
import com.example.todo_list.todo_list.data.repository.TaskRepositoryDB
import com.example.todo_list.todo_list.domain.entities.MyObjectBox
import dagger.hilt.android.HiltAndroidApp
import io.objectbox.BoxStore

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyApp", "Application onCreate called")
        ObjectBoxManager.init(this)
    }
}

//class MyApp : Application() {
//    lateinit var boxStore: BoxStore
//
//    override fun onCreate() {
//        super.onCreate()
//        boxStore = MyObjectBox.builder().androidContext(this).build()
//
//    }
//}