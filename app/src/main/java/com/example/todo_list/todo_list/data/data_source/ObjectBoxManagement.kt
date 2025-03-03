package com.example.todo_list.todo_list.data.data_source

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.todo_list.todo_list.domain.entities.MyObjectBox
import io.objectbox.BoxStore

//object ObjectBoxManager {
//    lateinit var boxStore: BoxStore
//        private set
//
//    fun init(context: Context) {
//        boxStore = MyObjectBox.builder()
//            .androidContext(context.applicationContext)
//            .build()
//    }
//}
object ObjectBoxManager {
    lateinit var boxStore: BoxStore
        private set
    fun init(app: Application): BoxStore {
        Log.d("ObjectBoxManager", "Before initializing BoxStore")
        boxStore = MyObjectBox.builder().androidContext(app).build()
        Log.d("ObjectBoxManager", "BoxStore initialized: ${boxStore != null}")
        return boxStore
    }
}
