package com.debduttapanda.idealapp.database

import com.debduttapanda.idealapp.room.TaskEntity

interface Database {
    suspend fun getAll(): List<TaskEntity>
    suspend fun loadAllByIds(taskIds: IntArray): List<TaskEntity>
    suspend fun findByTitle(title: String): TaskEntity
    suspend fun insertAll(vararg taskEntities: TaskEntity)
    suspend fun delete(taskEntity: TaskEntity)
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteAll()
}