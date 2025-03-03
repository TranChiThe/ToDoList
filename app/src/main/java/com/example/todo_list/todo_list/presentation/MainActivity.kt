package com.example.todo_list.todo_list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todo_list.todo_list.data.data_source.MyApp
import com.example.todo_list.todo_list.data.data_source.ObjectBoxManager
import com.example.todo_list.todo_list.data.repository.TaskRepositoryDB
import com.example.todo_list.todo_list.presentation.screen.MainScreen
import com.example.todo_list.ui.theme.ToDo_ListTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.todo_list.todo_list.domain.repository.TaskRepository as TaskRepository

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val app = application as MyApp
//        val repository = TaskRepositoryDB(ObjectBoxManager.boxStore)
        setContent {
            ToDo_ListTheme {
                MainScreen()
            }
        }
    }
}

