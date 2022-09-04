package com.debduttapanda.idealapp.repository

import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.idealapp.room.TaskEntity
import javax.inject.Inject

class LocalTaskRepositoryImpl @Inject constructor(
    private val tasks: LocalTasks
):TaskRepository {
    override suspend fun addTask(task: Task) {
        tasks.insertAll(TaskEntity(
            title = task.title,
            description = task.description,
            completed = task.completed
        ))
    }

    override suspend fun getAllTasks(): List<Task> {
        return tasks.getAll().map {
            it.task
        }
    }

    override suspend fun updateTask(task: Task) {
        tasks.updateTask(
            TaskEntity(
            title = task.title,
            description = task.description,
            completed = task.completed,
            uid = task.uid
        )
        )
    }

    override suspend fun deleteTask(task: Task) {
        tasks.delete(TaskEntity(
            uid = task.uid,
            title = task.title,
            description = task.description,
            completed = task.completed
        ))
    }

    override suspend fun deleteAllTask() {
        tasks.deleteAll()
    }

    override suspend fun getTaskById(id: Int): Task? {
        val result = tasks.loadAllByIds(intArrayOf(id))
        if(result.isEmpty()){
            return null
        }
        return result.first().task
    }
}