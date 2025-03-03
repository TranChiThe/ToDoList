package com.example.todo_list.todo_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todo_list.todo_list.data.data_source.ObjectBoxManager
import com.example.todo_list.todo_list.data.repository.TaskRepositoryDB
import com.example.todo_list.todo_list.domain.entities.Task
import com.example.todo_list.todo_list.domain.repository.TaskRepository
import com.example.todo_list.todo_list.domain.use_case.task_usecase.AddTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.DeleteTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.LoadTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.net.ssl.SSLEngineResult.Status

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val loadTaskUseCase: LoadTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase

): ViewModel () {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = loadTaskUseCase()
        }
    }

    fun addTask(title: String, status: String) {
        viewModelScope.launch {
            addTaskUseCase(title, status)
            loadTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
            loadTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase(task)
            loadTasks()
        }
    }
//    companion object {
//        fun factory(
//            loadTasksUseCase: LoadTaskUseCase,
//            addTaskUseCase: AddTaskUseCase,
//            updateTaskUseCase: UpdateTaskUseCase,
//            deleteTaskUseCase: DeleteTaskUseCase
//        ): ViewModelProvider.Factory {
//            return object : ViewModelProvider.Factory {
//                @Suppress("UNCHECKED_CAST")
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
//                        return TaskViewModel(
//                            loadTasksUseCase,
//                            addTaskUseCase,
//                            updateTaskUseCase,
//                            deleteTaskUseCase
//                        ) as T
//                    }
//                    throw IllegalArgumentException("Unknown ViewModel class")
//                }
//            }
//        }
//    }
}
