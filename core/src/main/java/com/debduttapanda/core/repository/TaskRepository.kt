package com.debduttapanda.core.repository

import com.debduttapanda.core.models.Task

interface TaskRepository {
    suspend fun addTask(task: Task)
    suspend fun getAllTasks(): List<Task>
}