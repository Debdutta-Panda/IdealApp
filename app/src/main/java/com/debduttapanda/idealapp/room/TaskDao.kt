package com.debduttapanda.idealapp.room

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE title LIKE :title")
    suspend fun findByTitle(title: String): TaskEntity

    @Insert
    suspend fun insertAll(vararg taskEntities: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM TaskEntity")
    suspend fun deleteAll()
}