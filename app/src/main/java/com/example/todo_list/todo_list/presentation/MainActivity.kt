package com.example.todo_list.todo_list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todo_list.todo_list.presentation.screen.MainScreen
import com.example.todo_list.ui.theme.ToDo_ListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDo_ListTheme {
                MainScreen()
            }
        }
    }
}

