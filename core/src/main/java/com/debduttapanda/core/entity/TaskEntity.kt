package com.debduttapanda.core.entity

import com.debduttapanda.core.models.Task

interface TaskEntity {
    suspend fun addTask(task: Task)
    suspend fun getAllTasks(): List<Task>
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteAllTask()
    suspend fun getTaskById(id: Int): Task?
}