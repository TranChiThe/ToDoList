package com.example.todo_list.di

import android.app.Application
import com.example.todo_list.todo_list.data.data_source.MyApp
import com.example.todo_list.todo_list.data.data_source.ObjectBoxManager
import com.example.todo_list.todo_list.data.repository.TaskRepositoryDB
import com.example.todo_list.todo_list.domain.repository.TaskRepository
import com.example.todo_list.todo_list.domain.use_case.task_usecase.AddTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.DeleteTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.LoadTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBoxStore(app: Application): BoxStore {
        return ObjectBoxManager.init(app)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(boxStore: BoxStore): TaskRepository = TaskRepositoryDB(boxStore)

    @Provides
    @Singleton
    fun provideLoadTasksUseCase(repository: TaskRepository) = LoadTaskUseCase(repository)

    @Provides
    @Singleton
    fun provideAddTaskUseCase(repository: TaskRepository) = AddTaskUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateTaskUseCase(repository: TaskRepository) = UpdateTaskUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(repository: TaskRepository) = DeleteTaskUseCase(repository)
}