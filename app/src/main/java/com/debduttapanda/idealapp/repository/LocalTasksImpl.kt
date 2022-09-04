package com.debduttapanda.idealapp.repository

import com.debduttapanda.idealapp.database.Database
import com.debduttapanda.idealapp.room.TaskEntity
import javax.inject.Inject

class LocalTasksImpl @Inject constructor(
    private val database: Database
) : LocalTasks {
    override suspend fun getAll(): List<TaskEntity> {
        return  database.getAll()
    }

    override suspend fun loadAllByIds(taskIds: IntArray): List<TaskEntity> {
        return database.loadAllByIds(taskIds)
    }

    override suspend fun findByTitle(title: String): TaskEntity {
        return database.findByTitle(title)
    }

    override suspend fun insertAll(vararg taskEntities: TaskEntity) {
        database.insertAll(*taskEntities)
    }

    override suspend fun delete(taskEntity: TaskEntity) {
        database.delete(taskEntity)
    }

    override suspend fun updateTask(task: TaskEntity) {
        database.updateTask(task)
    }

    override suspend fun deleteAll() {
        database.deleteAll()
    }
}