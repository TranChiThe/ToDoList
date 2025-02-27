package com.example.todo_list.todo_list.data.data_source

import android.content.Context
import com.example.todo_list.todo_list.domain.entities.MyObjectBox
import io.objectbox.BoxStore

object ObjectBoxManager {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}