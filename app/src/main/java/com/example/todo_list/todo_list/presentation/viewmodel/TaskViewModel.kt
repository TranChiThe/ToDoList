package com.example.todo_list.todo_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todo_list.todo_list.data.data_source.ObjectBoxManager
import com.example.todo_list.todo_list.data.repository.TaskRepositoryDB
import com.example.todo_list.todo_list.domain.entities.Task
import com.example.todo_list.todo_list.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.net.ssl.SSLEngineResult.Status

class TaskViewModel (private val repository: TaskRepository): ViewModel () {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getAllTasks()
        }
    }

    fun addTask(title: String, status: String) {
        viewModelScope.launch {
            val newTask = Task(title = title, status = status)
            repository.addTask(newTask)
            loadTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            loadTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            loadTasks()
        }
    }
    companion object {
        fun factory(repository: TaskRepository): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                        return TaskViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Lớp ViewModel không xác định")
                }
            }
        }
    }
}

//@HiltViewModel
//class TaskViewModel @Inject constructor(
//    private val repository: TaskRepository
//) : ViewModel() {
//    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
//    val tasks: StateFlow<List<Task>> get() = _tasks
//
//    init {
//        loadTasks()
//    }
//
//    private fun loadTasks() {
//        viewModelScope.launch {
//            _tasks.value = repository.getAllTasks()
//        }
//    }
//
//    fun addTask(title: String, status: String) {
//        viewModelScope.launch {
//            val newTask = Task(title = title, status = status)
//            repository.addTask(newTask)
////            loadTasks()
//        }
//    }
//}