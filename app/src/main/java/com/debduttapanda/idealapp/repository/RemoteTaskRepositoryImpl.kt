package com.debduttapanda.idealapp.repository

import com.debduttapanda.core.models.Task
import com.debduttapanda.core.repository.TaskRepository
import com.debduttapanda.idealapp.remote.TaskApi
import com.debduttapanda.idealapp.room.TaskEntity
import javax.inject.Inject

class RemoteTaskRepositoryImpl @Inject constructor(
    private val tasks: TaskApi
):TaskRepository {
    override suspend fun addTask(task: Task) {
        val r = tasks.createTasks(listOf(task))
    }

    override suspend fun getAllTasks(): List<Task> {
        val r = tasks.getTasks()
        return if(r.errorBody()!=null){
            emptyList()
        } else{
            r.body()?.tasks?: emptyList()
        }
    }

    override suspend fun updateTask(task: Task) {
        tasks.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        tasks.deleteTask(task.uid)
    }

    override suspend fun deleteAllTask() {
        tasks.deleteAll()
    }

    override suspend fun getTaskById(id: Int): Task? {
        val result = tasks.getByIds(id.toString())
        return if(result.errorBody()!=null){
            null
        } else{
            result.body()?.tasks?.getOrNull(0)
        }
    }
}