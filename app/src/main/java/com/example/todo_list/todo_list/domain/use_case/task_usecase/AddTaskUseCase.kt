package com.example.todo_list.todo_list.domain.use_case.task_usecase

import com.example.todo_list.todo_list.domain.entities.Task
import com.example.todo_list.todo_list.domain.repository.TaskRepository

class AddTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(title: String, status: String) {
        val newTask = Task(title = title.trim(), status = status)
        repository.addTask(newTask)
    }
}