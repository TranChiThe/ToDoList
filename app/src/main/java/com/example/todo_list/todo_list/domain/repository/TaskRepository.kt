package com.example.todo_list.todo_list.domain.repository

import com.example.todo_list.todo_list.domain.entities.Task

interface TaskRepository {
    fun addTask(task: Task)
    fun getAllTasks(): List<Task>
    fun updateTask(task: Task)
    fun deleteTask(task: Task)
}