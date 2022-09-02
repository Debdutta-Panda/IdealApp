package com.debduttapanda.idealapp.repository

import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.idealapp.room.TaskDao
import com.debduttapanda.idealapp.room.TaskEntity
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
):TaskRepository {
    override suspend fun addTask(task: Task) {
        taskDao.insertAll(TaskEntity(
            title = task.title,
            description = task.description,
            completed = task.completed
        ))
    }

    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAll().map {
            it.task
        }
    }
}