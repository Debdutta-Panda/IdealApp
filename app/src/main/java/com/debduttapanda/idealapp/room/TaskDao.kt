package com.debduttapanda.idealapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Task>

    @Query("SELECT * FROM task WHERE title LIKE :title")
    fun findByTitle(title: String): Task

    @Insert
    fun insertAll(vararg tasks: Task)

    @Delete
    fun delete(task: Task)
}