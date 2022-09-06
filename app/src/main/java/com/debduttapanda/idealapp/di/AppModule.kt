package com.debduttapanda.idealapp.di

import android.content.Context
import androidx.room.Room
import com.debduttapanda.core.BuildConfig
import com.debduttapanda.core.entity.TaskEntity
import com.debduttapanda.core.entity.TaskEntityImpl
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.core.use_cases.*
import com.debduttapanda.core.use_cases.impl.*
import com.debduttapanda.idealapp.database.Database
import com.debduttapanda.idealapp.database.RoomDatabaseImpl
import com.debduttapanda.idealapp.remote.TaskApi
import com.debduttapanda.idealapp.repository.LocalTasks
import com.debduttapanda.idealapp.repository.LocalTasksImpl
import com.debduttapanda.idealapp.repository.LocalTaskRepositoryImpl
import com.debduttapanda.idealapp.repository.RemoteTaskRepositoryImpl
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
    fun provideTaskRepository(localTasks: LocalTasks,taskApi: TaskApi): TaskRepository {
        return if(com.debduttapanda.idealapp.BuildConfig.FLAVOR == "local"){
            LocalTaskRepositoryImpl(localTasks)
        } else{
            RemoteTaskRepositoryImpl(taskApi)
        }
    }

    @Provides
    @Singleton
    fun provideLocalTasks(database: Database): LocalTasks {
        return LocalTasksImpl(database)
    }

    @Provides
    @Singleton
    fun provideDatabase(taskDao: TaskDao): Database {
        return RoomDatabaseImpl(taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskEntity(taskRepository: TaskRepository): TaskEntity {
        return TaskEntityImpl(taskRepository)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindAddTaskUseCase(taskEntity: TaskEntity): AddTaskUseCase {
        return AddTaskUseCaseImpl(taskEntity)
    }

    @Provides
    @ViewModelScoped
    fun bindGetAllTaskUseCase(taskEntity: TaskEntity): GetAllTaskUseCase {
        return GetAllTaskUseCaseImpl(taskEntity)
    }

    @Provides
    @ViewModelScoped
    fun bindUpdateTaskUseCase(taskEntity: TaskEntity): UpdateTaskUseCase {
        return UpdateTaskUseCaseImpl(taskEntity)
    }

    @Provides
    @ViewModelScoped
    fun bindDeleteTaskUseCase(taskEntity: TaskEntity): DeleteTaskUseCase {
        return DeleteTaskUseCaseImpl(taskEntity)
    }

    @Provides
    @ViewModelScoped
    fun bindDeleteAllTaskUseCase(taskEntity: TaskEntity): DeleteAllTaskUseCase {
        return DeleteAllTaskUseCaseImpl(taskEntity)
    }

    @Provides
    @ViewModelScoped
    fun bindGetTaskUseCase(taskEntity: TaskEntity): GetTaskUseCase {
        return GetTaskUseCaseImpl(taskEntity)
    }
}

