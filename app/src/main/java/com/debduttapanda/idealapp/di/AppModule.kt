package com.debduttapanda.idealapp.di

import android.content.Context
import androidx.room.Room
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.core.use_cases.*
import com.debduttapanda.core.use_cases.impl.*
import com.debduttapanda.idealapp.repository.TaskRepositoryImpl
import com.debduttapanda.idealapp.room.AppDatabase
import com.debduttapanda.idealapp.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
        callback: AppDatabase.Callback
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "my_database"
        )
        .addCallback(callback)
        .build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindAddTaskUseCase(taskRepository: TaskRepository): AddTaskUseCase {
        return AddTaskUseCaseImpl(taskRepository)
    }

    @Provides
    @ViewModelScoped
    fun bindGetAllTaskUseCase(taskRepository: TaskRepository): GetAllTaskUseCase {
        return GetAllTaskUseCaseImpl(taskRepository)
    }

    @Provides
    @ViewModelScoped
    fun bindUpdateTaskUseCase(taskRepository: TaskRepository): UpdateTaskUseCase {
        return UpdateTaskUseCaseImpl(taskRepository)
    }

    @Provides
    @ViewModelScoped
    fun bindDeleteTaskUseCase(taskRepository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCaseImpl(taskRepository)
    }

    @Provides
    @ViewModelScoped
    fun bindDeleteAllTaskUseCase(taskRepository: TaskRepository): DeleteAllTaskUseCase {
        return DeleteAllTaskUseCaseImpl(taskRepository)
    }
}

