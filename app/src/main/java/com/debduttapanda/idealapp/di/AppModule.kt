package com.debduttapanda.idealapp.di

import android.content.Context
import androidx.room.Room
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.core.use_cases.AddTaskUseCase
import com.debduttapanda.core.use_cases.GetAllTaskUseCase
import com.debduttapanda.core.use_cases.impl.AddTaskUseCaseImpl
import com.debduttapanda.core.use_cases.impl.GetAllTaskUseCaseImpl
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
}

