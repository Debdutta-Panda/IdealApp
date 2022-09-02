package com.debduttapanda.core.repository

import com.debduttapanda.core.models.Task

interface TaskRepository {
    suspend fun addTask(task: Task)
    suspend fun getAllTasks(): List<Task>
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteAllTask()
}