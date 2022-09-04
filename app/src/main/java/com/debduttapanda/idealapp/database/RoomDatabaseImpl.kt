package com.debduttapanda.idealapp.database

import com.debduttapanda.idealapp.room.TaskDao
import com.debduttapanda.idealapp.room.TaskEntity
import javax.inject.Inject

class RoomDatabaseImpl @Inject constructor(
    private val taskDao: TaskDao
): Database {
    override suspend fun getAll(): List<TaskEntity> {
        return taskDao.getAll()
    }

    override suspend fun loadAllByIds(taskIds: IntArray): List<TaskEntity> {
        return taskDao.loadAllByIds(taskIds)
    }

    override suspend fun findByTitle(title: String): TaskEntity {
        return taskDao.findByTitle(title)
    }

    override suspend fun insertAll(vararg taskEntities: TaskEntity) {
        taskDao.insertAll(*taskEntities)
    }

    override suspend fun delete(taskEntity: TaskEntity) {
        taskDao.delete(taskEntity)
    }

    override suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    override suspend fun deleteAll() {
        taskDao.deleteAll()
    }
}