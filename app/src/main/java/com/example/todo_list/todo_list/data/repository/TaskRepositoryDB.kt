package com.example.todo_list.todo_list.data.repository

import com.example.todo_list.todo_list.domain.entities.Task
import com.example.todo_list.todo_list.domain.repository.TaskRepository
import io.objectbox.Box
import io.objectbox.BoxStore

class TaskRepositoryDB (private val boxStore: BoxStore) : TaskRepository {
    private val taskBox: Box<Task> = boxStore.boxFor(Task::class.java)

    override fun addTask(task: Task) {
        taskBox.put(task)
    }

    override fun getAllTasks(): List<Task> {
        return taskBox.all
    }

    override fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: Task) {
        taskBox.remove(task)
    }
}