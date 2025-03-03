package com.example.todo_list.todo_list.domain.use_case.task_usecase

import com.example.todo_list.todo_list.domain.entities.Task
import com.example.todo_list.todo_list.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}